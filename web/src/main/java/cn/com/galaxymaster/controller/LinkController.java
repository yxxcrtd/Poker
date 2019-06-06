package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.Link;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import freemarker.template.TemplateException;

/**
 * Link Controller
 */
@RestController
	@RequestMapping("manage/link")
public class LinkController extends BaseController {

	/**
	 * List
	 * 
	 * @param p 分页
	 * @param k 关键字
	 * @param s1 第一个下拉条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k,
			@RequestParam(value = "s1", required = false) String s1) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : "\"LinkName\" like " + like;
		mav.addObject("s1", s1);
		if (null != s1 && !"".equals(s1)) {
			k += " AND \"LinkStatus\" = " + s1;
		}
		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = linkService.findAllCount(link, k);
		pager.setTotalCount(count);
		mav.addObject("linkList", linkService.findByPager(pager, k, "\"LinkStatus\", \"LinkOrderby\" ASC "));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("status", null == s1 ? "" : s1); // 列表页面中首先显示全部
		mav.addObject("active", "link");
		mav.setViewName("link/LinkList");
		return mav;
	}

	/**
	 * Search List
	 * 
	 * @return
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView listByNameAndStatus(@RequestParam(value = "p", required = true, defaultValue = "1") int p,
			@RequestParam(value = "s", required = true, defaultValue = "2") int status, @RequestParam(value = "n", required = true, defaultValue = "") String name) {
		String where = linkService.generateWhere(name, status);
		if (where != null) {
			ModelAndView mav = new ModelAndView();
			Pager pager = new Pager();
			pager.setPageNo(p);
			int count = linkService.findCount(where.toString());
			pager.setTotalCount(count);
			mav.addObject("linkList", linkService.findByPager(pager, where.toString(), "\"LinkOrderby\""));
			mav.addObject("pager", pager);
			mav.addObject("count", count);
			mav.addObject("search_name", name);
			mav.addObject("status", status);
			mav.addObject("active", "link");
			mav.setViewName("link/LinkList");
			return mav;
		} else {
			LOGGER.info("search Parameter Error");
			return new ModelAndView("redirect:/manage/link");
		}
	}

	/**
	 * Edit
	 * 
	 * @param linkId
	 * @return
	 */
	@RequestMapping("edit/{linkId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "linkId") int linkId) {
		if (0 == linkId) {
			link = new Link();
			link.setLinkId(linkId);
			link.setLinkOrderby(1);
		} else {
			link = linkService.findById(linkId);
		}
		return mav(link, "link", "link/LinkEdit");
	}

	/**
	 * Save
	 * 
	 * @param link
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("link") @Valid Link link, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			return mav(link, "link", "link/LinkEdit");
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				link.setLinkLogo(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		if (0 == link.getLinkId()) {
			linkService.save(link);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			linkService.update(link);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", linkService.findAllList("\"LinkStatus\" = 0", "\"LinkOrderby\""));
		map.put("request", request);
		String title = 0 == link.getLinkId() ? "新增友情链接：" : "修改友情链接：";
		logService.saveLog(request, "link", title + link.getLinkName());
		generateHTML(map, request);

		return new ModelAndView("redirect:/manage/link");
	}

	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public @ResponseBody void updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 请求参数:id数组、状态码
		String[] ids = request.getParameterValues("idList[]");
		String status = request.getParameter("status");
		if (ids == null || ids.length == 0 || StringUtils.isEmpty(ids[0]) || StringUtils.isEmpty(status)) {
			LOGGER.error("update Link Status Parameter Error");
			throw new Exception("update Link Status Parameter Error");
		} else {
			linkService.updateStatus(ids, status);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", linkService.findAllList("\"LinkStatus\" = 0", "\"LinkOrderby\""));
			map.put("request", request);
			String s = "0".equals(status) ? "显示" : "隐藏";
			logService.saveLog(request, "link", "更新友情链接状态为：" + s);
			generateHTML(map, request);
		}
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
		FileUtil.generateHTML("WEB-INF/ftl/link", "Link.ftl", "link.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
	}

}
