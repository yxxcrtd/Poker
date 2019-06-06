package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import cn.com.galaxymaster.domain.Activity;
import cn.com.galaxymaster.domain.Game;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import cn.com.galaxymaster.util.TaskExecutor;
import freemarker.template.TemplateException;

/**
 * Activity Controller
 */
@Controller
@RequestMapping("manage/activity")
public class ActivityController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "s1", required = false) String s1,
			@RequestParam(value = "s2", required = false) String s2, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : "\"ActivityTitle\" like " + like;

		mav.addObject("s1", s1);
		if (null != s1 && !"".equals(s1)) {
			k += " AND \"ActivityStatus\" = " + s1;
		}
		mav.addObject("s2", s2);
		if (null != s2 && !"".equals(s2)) {
			k += " AND \"ActivityCategory\" = " + s2;
		}

		pager = new Pager();
		pager.setPageNo(p);
		int count = activityService.findAllCount(activity, k);
		pager.setTotalCount(count);
		mav.addObject("list", activityService.findByPager(pager, k, "\"ActivityStatus\", \"ActivityOrderby\", \"ActivityCreateTime\" DESC"));
		mav.addObject("categoryMap", getNewsCategoryMap());
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "activity");
		mav.addObject("status1", null == s1 ? "" : s1);
		mav.addObject("status2", null == s2 ? "" : s2);
		mav.setViewName("activity/ActivityList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param activityId
	 * @return
	 */
	@RequestMapping("edit/{activityId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "activityId") int activityId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("statusMap", getActivityStatusMap());
		mav.addObject("categoryMap", getActivityCategoryMap());
		// mav.addObject("provinceMap", getProvinceMap());
		// mav.addObject("cityMap", getCityMap());
		if (0 == activityId) {
			activity = new Activity();
			activity.setActivityId(activityId);
			activity.setActivityOrderby(1);
			activity.setActivityHit(0);
			activity.setActivityStatus(0);
		} else {
			activity = activityService.findById(activityId);
		}
		mav.addObject("gamesMap", getCategoryMap());
		mav.addObject("activity", activity);
		mav.setViewName("activity/ActivityEdit");
		mav.addObject("active", "activity");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param activity
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("activity") @Valid Activity activity, BindingResult result, final RedirectAttributes redirectAttributes,
			MultipartHttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			// mav.addObject("provinceMap", getProvinceMap());
			// mav.addObject("cityMap", getCityMap());
			mav.addObject("statusMap", getActivityStatusMap());
			mav.addObject("categoryMap", getActivityCategoryMap());
			mav.setViewName("activity/ActivityEdit");
			mav.addObject("activity", activity);
			mav.addObject("active", "activity");
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				activity.setActivityPicture(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		if (0 == activity.getActivityId()) {
			activityService.save(activity);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			activityService.update(activity);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}
		String title = 0 == activity.getActivityId() ? "新增活动：" : "修改活动：";
		logService.saveLog(request, "activity", title + activity.getActivityTitle());
		generateHTML(request);

		return new ModelAndView("redirect:/manage/activity");
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
			LOGGER.error("update Activity Status Parameter Error");
			throw new Exception("update Activity Status Parameter Error");
		} else {
			activityService.updateStatus(ids, status);
			String s = "1".equals(status) ? "发布" : "停止";
			logService.saveLog(request, "activity", "更新活动状态为：" + s);
		}
		generateHTML(request);
	}

	private Map<String, String> getCategoryMap() {
		Map<String, String> categoryMap = new LinkedHashMap<String, String>();
		// 查询游戏库
		List<Game> games = gameService.findAllList("", " \"GameOrderby\" ASC");
		for (Game game : games) {
			categoryMap.put(game.getGameId() + "", game.getGameTitle());
		}
		return categoryMap;
	}

	/**
	 * Generate HTML File
	 * 
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateHTML(final HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("request", request);
		map.put("cur", "activity");

		// 生成详情页 TODO 以后单独生成
		List<Activity> list = activityService.findAllList("\"ActivityStatus\" > 0", "\"ActivityCreateTime\" DESC");
		map.put("list", list);
		for (Activity action : list) {
			map.put("action", action);
			FileUtil.generateHTML("WEB-INF/ftl", "activity/ActivityDetail.ftl", "action" + action.getActivityId() + ".html", map, request.getSession().getServletContext(),
					UPLOAD_PATH);
		}

		// 生成二级页面的牌类
		map.put("category_0_list", activityService.findAllList("\"ActivityCategory\" = 0 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC"));
		// 生成二级页面的棋类
		map.put("category_1_list", activityService.findAllList("\"ActivityCategory\" = 1 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC"));
		// 生成二级页面的麻将
		map.put("category_2_list", activityService.findAllList("\"ActivityCategory\" = 2 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC"));

		// 生成二级活动页面
		FileUtil.generateHTML("WEB-INF/ftl", "activity/Activity.ftl", "action.html", map, request.getSession().getServletContext(),
				UPLOAD_PATH.substring(0, UPLOAD_PATH.lastIndexOf("upload")));
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
