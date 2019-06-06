package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.Date;
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

import cn.com.galaxymaster.domain.Log;
import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.domain.News;
import cn.com.galaxymaster.util.Constants;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import cn.com.galaxymaster.util.TaskExecutor;
import freemarker.template.TemplateException;

/**
 * News Controller
 */
@Controller
@RequestMapping("manage/news")
public class NewsController extends BaseController {

	/**
	 * List
	 * 
	 * @param p 分页
	 * @param k 关键字
	 * @param s1 第一个下拉条件
	 * @param s2 第二个下拉条件
	 * @param s3 第三个下拉条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "s1", required = false) String s1,
			@RequestParam(value = "s2", required = false) String s2, @RequestParam(value = "s3", required = false) String s3, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : "\"NewsTitle\" like " + like;

		mav.addObject("s1", s1);
		if (null != s1 && !"".equals(s1)) {
			k += " AND \"NewsCategory\" = " + s1;
		}
		mav.addObject("s2", s2);
		if (null != s2 && !"".equals(s2)) {
			k += " AND \"NewsType\" = " + s2;
		}
		mav.addObject("s3", s3);
		if (null != s3 && !"".equals(s3)) {
			k += " AND \"NewsStatus\" = " + s3;
		}

		pager = new Pager();
		pager.setPageNo(p);
		int count = newsService.findAllCount(news, k);
		pager.setTotalCount(count);
		mav.addObject("newsList", newsService.findByPager(pager, k, "\"NewsStatus\", \"NewsOrderby\", \"NewsCreateTime\" DESC"));
		mav.addObject("categoryMap", getNewsCategoryMap());
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "news");
		mav.addObject("status1", null == s1 ? "" : s1);
		mav.addObject("status2", null == s2 ? "" : s2);
		mav.addObject("status3", null == s3 ? "" : s3);
		mav.setViewName("news/NewsList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param newsId
	 * @return
	 */
	@RequestMapping("edit/{newsId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "newsId") int newsId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("statusMap", getStatusMap());
		mav.addObject("categoryMap", getNewsCategoryMap());
		mav.addObject("typeMap", getNewsTypeMap());
		if (0 == newsId) {
			news = new News();
			news.setNewsId(newsId);
			news.setNewsOrderby(1);
			news.setNewsHit(0);
			news.setNewsStatus(0);
		} else {
			news = newsService.findById(newsId);
		}
		mav.addObject("news", news);
		mav.addObject("active", "news");
		mav.setViewName("news/NewsEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param news
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("news") @Valid News news, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("news", news);
			mav.addObject("active", "news");
			mav.setViewName("news/NewsEdit");
			mav.addObject("statusMap", getStatusMap());
			mav.addObject("categoryMap", getNewsCategoryMap());
			mav.addObject("typeMap", getNewsTypeMap());
			LOGGER.error(result.getAllErrors().toString());
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				news.setNewsPicture(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		log = new Log();
		Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if (0 == news.getNewsId()) {
			newsService.save(news);
			log.setLogTitle("新增新闻" + news.getNewsTitle());
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			newsService.update(news);
			log.setLogTitle("修改新闻" + news.getNewsTitle());
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		generateHTML(request);

		log.setLogObj("news");
		log.setLogCreateTime(new Date());
		log.setLogUser(manager.getManagerName());
		log.setLogIP(request.getRemoteAddr());
		logService.save(log);

		return new ModelAndView("redirect:/manage/news");
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
			LOGGER.error("update news Status Parameter Error");
			throw new Exception("update news Status Parameter Error");
		} else {
			newsService.updateStatus(ids, status);
			String s = "0".equals(status) ? "显示" : "隐藏";
			logService.saveLog(request, "news", "更新新闻状态为：" + s);
		}
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
		map.put("cur", "news");

		// 生成详情页 TODO 以后单独生成
		List<News> list = newsService.findAllList("\"NewsStatus\" = 0", "\"NewsCreateTime\" DESC");
		map.put("list", list);

		// 生成二级新闻页面 - 新闻关注排行
		map.put("hotNews", newsService.findAllList("\"NewsStatus\" = 0", "\"NewsHit\" DESC"));

		for (News news : list) {
			map.put("news", news);
			if (null != news.getNewsKeyword() && !"".equals(news.getNewsKeyword())) {
				map.put("keywords", newsService.findAllList("\"NewsKeyword\" LIKE '%" + news.getNewsKeyword() + "%'", "\"NewsCreateTime\" DESC")); // TODO
			}
			FileUtil.generateHTML("WEB-INF/ftl", "news/NewsDetail.ftl", "news" + news.getNewsId() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		}

		// 生成首页新闻资讯 - 德州扑克
		map.put("category_1_list", newsService.findAllList("\"NewsCategory\" = 1 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC"));
		// 生成首页新闻资讯 - 斗地主
		map.put("category_2_list", newsService.findAllList("\"NewsCategory\" = 2 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC"));
		// 生成首页新闻资讯 - 麻将
		map.put("category_3_list", newsService.findAllList("\"NewsCategory\" = 3 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC"));
		// 生成首页新闻资讯 - 棋类
		map.put("category_4_list", newsService.findAllList("\"NewsCategory\" = 4 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC"));
		// 生成首页新闻资讯 - 其他
		map.put("category_9_list", newsService.findAllList("\"NewsCategory\" = 9 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC"));
		FileUtil.generateHTML("WEB-INF/ftl", "news/NewsIndex.ftl", "index_news.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		// 生成二级新闻页面 - 综合新闻
		map.put("category_0_list", newsService.findAllList("\"NewsCategory\" = 0 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC"));

		// 生成二级新闻页面
		FileUtil.generateHTML("WEB-INF/ftl", "news/News.ftl", "news.html", map, request.getSession().getServletContext(),
				UPLOAD_PATH.substring(0, UPLOAD_PATH.lastIndexOf("upload")));
		FileUtil.generateHTML("WEB-INF/ftl", "news/NewsList0.ftl", "news_list.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		FileUtil.generateHTML("WEB-INF/ftl", "news/NewsList1.ftl", "news_list1.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		FileUtil.generateHTML("WEB-INF/ftl", "news/NewsList2.ftl", "news_list2.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		FileUtil.generateHTML("WEB-INF/ftl", "news/NewsList3.ftl", "news_list3.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		FileUtil.generateHTML("WEB-INF/ftl", "news/NewsList4.ftl", "news_list4.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		FileUtil.generateHTML("WEB-INF/ftl", "news/NewsList9.ftl", "news_list9.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
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
