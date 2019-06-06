package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.Ad;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import freemarker.template.TemplateException;

/**
 * Ad Controller
 */
@RestController
@RequestMapping("manage/ad")
public class AdController extends BaseController {

	/**
	 * List
	 * 
	 * @param p 分页
	 * @param k 关键字
	 * @param s1 第一个下拉条件
	 * @param s2 第二个下拉条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k,
			@RequestParam(value = "s1", required = false) String s1, @RequestParam(value = "s2", required = false) String s2) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : "\"AdTitle\" like " + like;

		mav.addObject("s1", s1);
		if (null != s1 && !"".equals(s1)) {
			k += " AND \"AdLocation\" = " + s1;
		}

		mav.addObject("s2", s2);
		if (null != s2 && !"".equals(s2)) {
			k += " AND \"AdVisible\" = " + s2;
		}

		pager = new Pager();
		pager.setPageNo(p);
		int count = adService.findAllCount(ad, k);
		pager.setTotalCount(count);
		mav.addObject("list", adService.findByPager(pager, k, "\"AdVisible\", \"AdLocation\", \"AdOrderby\""));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "ad");
		mav.addObject("status1", null == s1 ? "" : s1); // 列表页面中首先显示全部
		mav.addObject("status2", null == s2 ? "" : s2); // 列表页面中首先显示全部
		mav.setViewName("ad/AdList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param adId
	 * @return
	 */
	@RequestMapping("edit/{adId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "adId") int adId) {
		ModelAndView mav = new ModelAndView();
		if (0 == adId) {
			ad = new Ad();
			ad.setAdId(adId);
			ad.setAdOrderby(1);
		} else {
			ad = adService.findById(adId);
		}
		mav.addObject("visibleMap", getAdVisibleMap());
		mav.addObject("locationMap", getAdLocationMap());
		mav.addObject("ad", ad);
		mav.setViewName("ad/AdEdit");
		mav.addObject("active", "ad");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param ad
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("ad") @Valid Ad ad, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("visibleMap", getAdVisibleMap());
			mav.addObject("locationMap", getAdLocationMap());
			mav.setViewName("ad/AdEdit");
			mav.addObject("ad", ad);
			mav.addObject("active", "ad");
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				ad.setAdPicture(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		if (0 == ad.getAdId()) {
			adService.save(ad);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			adService.update(ad);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}
		String title = 0 == ad.getAdId() ? "新增广告：" : "修改广告：";
		logService.saveLog(request, "ad", title + ad.getAdTitle());
		generateHTML(request);

		return new ModelAndView("redirect:/manage/ad");
	}

	/**
	 * UpdateStatus
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public void updateStatus(HttpServletRequest request, @RequestParam(value = "ids[]", required = true) String[] ids, @RequestParam(value = "status", required = true) int status)
			throws Exception {
		adService.updateStatus(ids, status);
		String s = "0".equals(status) ? "显示" : "隐藏";
		logService.saveLog(request, "ad", "更新广告状态为：" + s);
		generateHTML(request);
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateHTML(final HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("request", request);

		map.put("list", adService.findAllList("\"AdLocation\" = 1 AND \"AdVisible\" = 0", "\"AdOrderby\""));
		FileUtil.generateHTML("WEB-INF/ftl/ad", "AdIndex.ftl", "ad_index.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		map.put("adList", adService.findAllList("\"AdLocation\" = 2 AND \"AdVisible\" = 0", "\"AdOrderby\""));
		FileUtil.generateHTML("WEB-INF/ftl/ad", "AdNews.ftl", "ad_news.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		map.put("gameList", adService.findAllList("\"AdLocation\" = 3 AND \"AdVisible\" = 0", "\"AdOrderby\""));
		FileUtil.generateHTML("WEB-INF/ftl/ad", "AdGame.ftl", "ad_game.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		map.put("matchList", adService.findAllList("\"AdLocation\" = 4 AND \"AdVisible\" = 0", "\"AdOrderby\""));
		FileUtil.generateHTML("WEB-INF/ftl/ad", "AdMatch.ftl", "ad_match.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		map.put("actionList", adService.findAllList("\"AdLocation\" = 5 AND \"AdVisible\" = 0", "\"AdOrderby\""));
		FileUtil.generateHTML("WEB-INF/ftl/ad", "AdAction.ftl", "ad_action.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		map.put("videoList", adService.findAllList("\"AdLocation\" = 6 AND \"AdVisible\" = 0", "\"AdOrderby\""));
		FileUtil.generateHTML("WEB-INF/ftl/ad", "AdVideo.ftl", "ad_video.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		map.put("classList", adService.findAllList("\"AdLocation\" = 7 AND \"AdVisible\" = 0", "\"AdOrderby\""));
		FileUtil.generateHTML("WEB-INF/ftl/ad", "AdClass.ftl", "ad_class.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
	}

}
