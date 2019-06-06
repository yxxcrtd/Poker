package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.Tutorial;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import cn.com.galaxymaster.util.TaskExecutor;
import freemarker.template.TemplateException;

/**
 * tutorial Controller
 */
@Controller
@RequestMapping("manage/tutorial")
public class TutorialController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "s1", required = true, defaultValue = "0") int category,
			@RequestParam(value = "s2", required = true, defaultValue = "2") int status, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("s1", category);// 类型
		mav.addObject("s2", status);// 状态
		mav.addObject("k", k);// 查询字符
		/* 查询条件 */
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : like;
		StringBuffer where = new StringBuffer();
		if (status != 2) {
			where.append("\"TutorialStatus\"=").append(status);
		}
		if (category != 0) {
			if (where.length() > 2) {
				where.append(" AND ");
			}
			where.append("\"TutorialCategory\" =").append(category);
		}
		if (!StringUtils.isEmpty(k)) {
			if (where.length() > 2) {
				where.append(" AND ");
			}
			where.append("\"TutorialTitle\" LIKE ").append(k);
		}

		/* 排序条件 */
		String order = "";
		order = " \"TutorialOrderby\" ASC, \"TutorialId\" DESC";

		/* 分页条件 */
		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = tutorialService.findAllCount(tutorial, where.toString());
		pager.setTotalCount(count);

		/* 封装结果 */
		mav.addObject("list", tutorialService.findByPager(pager, where.toString(), order));
		mav.addObject("categoryMap", getTutorialCategoryMap());
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "tutorial");
		mav.setViewName("tutorial/TutorialList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param tutorialId
	 * @return
	 */
	@RequestMapping("edit/{tutorialId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "tutorialId") int tutorialId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("statusMap", getStatusMap());
		mav.addObject("categoryMap", getTutorialCategoryMap());
		if (0 == tutorialId) {
			tutorial = new Tutorial();
			tutorial.setTutorialId(tutorialId);
			tutorial.setTutorialCategory(1);
			tutorial.setTutorialOrderby(1);
			tutorial.setTutorialHit(0);
			tutorial.setTutorialStatus(0);
		} else {
			tutorial = tutorialService.findById(tutorialId);
		}
		mav.addObject("tutorial", tutorial);
		mav.addObject("active", "tutorial");
		mav.setViewName("tutorial/TutorialEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param tutorial
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("tutorial") @Valid Tutorial tutorial, BindingResult result, final RedirectAttributes redirectAttributes,
			MultipartHttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("tutorial", tutorial);
			mav.addObject("active", "tutorial");
			mav.setViewName("tutorial/TutorialEdit");
			mav.addObject("statusMap", getStatusMap());
			mav.addObject("categoryMap", getTutorialCategoryMap());
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				tutorial.setTutorialPicture(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		if (0 == tutorial.getTutorialId()) {
			tutorialService.save(tutorial);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			tutorialService.update(tutorial);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}
		String title = 0 == tutorial.getTutorialId() ? "新增教程：" : "修改教程：";
		logService.saveLog(request, "tutorial", title + tutorial.getTutorialTitle());
		generateHTML(request);

		return new ModelAndView("redirect:/manage/tutorial");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public @ResponseBody void updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 请求参数:id数组、状态码
		String[] ids = request.getParameterValues("idList[]");
		String status = request.getParameter("status");
		if (ids == null || ids.length == 0 || StringUtils.isEmpty(ids[0]) || StringUtils.isEmpty(status)) {
			LOGGER.error("update tutorial Status Parameter Error");
			throw new Exception("update tutorial Status Parameter Error");
		} else {
			tutorialService.updateStatus(ids, status);
			String s = "0".equals(status) ? "显示" : "隐藏";
			logService.saveLog(request, "tutorial", "更新教程状态为："+s);
			generateHTML(request);
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
	protected void generateHTML(final HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("request", request);
		map.put("cur", "tutorial");

		// 生成详情页 TODO 以后单独生成
		List<Tutorial> list = tutorialService.findAllList("\"TutorialStatus\" = 0", "\"TutorialCreateTime\" DESC");
		map.put("list", list);

		// 生成二级页面 - 关注排行
		map.put("hotTutorial", tutorialService.findAllList("\"TutorialStatus\" = 0", "\"TutorialHit\",\"TutorialCreateTime\" DESC"));

		// 列表页
		FileUtil.generateHTML("WEB-INF/ftl", "tutorial/tutorial_list.ftl", "tutorial_list.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		for (Tutorial tutorial : list) {
			map.put("tutorial", tutorial);
			if (null != tutorial.getTutorialKeyword() && !"".equals(tutorial.getTutorialKeyword())) {
				map.put("keywords", tutorialService.findAllList("\"TutorialKeyword\" LIKE '%" + tutorial.getTutorialKeyword() + "%'", "\"TutorialCreateTime\" DESC")); // TODO
			}
			FileUtil.generateHTML("WEB-INF/ftl", "tutorial/tutorial_detail.ftl", "tutorial" + tutorial.getTutorialId() + ".html", map, request.getSession().getServletContext(),
					UPLOAD_PATH);
		}

		// 生成 - 德州扑克
		map.put("category_1_list", tutorialService.findAllList("\"TutorialCategory\" = 1 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC"));
		// 生成 - 斗地主
		map.put("category_2_list", tutorialService.findAllList("\"TutorialCategory\" = 2 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC"));
		// 生成 - 麻将
		map.put("category_3_list", tutorialService.findAllList("\"TutorialCategory\" = 3 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC"));
		// 生成 - 棋类
		map.put("category_4_list", tutorialService.findAllList("\"TutorialCategory\" = 4 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC"));
		// 生成 - 其他
		map.put("category_9_list", tutorialService.findAllList("\"TutorialCategory\" = 9 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC"));
		FileUtil.generateHTML("WEB-INF/ftl", "tutorial/tutorial_class.ftl", "class_tutorial.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		// 修改新闻、广告、赛事、活动、棋牌教程时多线程更新游戏库
		final ServletContext servletContext = request.getSession().getServletContext();
		TaskExecutor.addTask(new Runnable() {
			@Override
			public void run() {
				try {
					refreshGameHTML(servletContext);
				} catch (Exception e) {
					LOGGER.error("update GameHtml error");
					e.printStackTrace();
				}
			}
		});
	}
}
