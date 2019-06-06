package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.Match;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import cn.com.galaxymaster.util.TaskExecutor;
import freemarker.template.TemplateException;

/**
 * Match Controller
 */
@Controller
@RequestMapping("manage/match")
public class MatchController extends BaseController {

	/**
	 * List
	 * 
	 * @param p
	 *            分页
	 * @param k
	 *            关键字
	 * @param s1
	 *            第一个下拉条件
	 * @param s2
	 *            第二个下拉条件
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k,
			@RequestParam(value = "s1", required = false) String s1) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : "\"MatchName\" like " + like;

		mav.addObject("s1", s1);
		if (null != s1 && !"".equals(s1)) {
			k += " AND \"MatchStatus\" = " + s1;
		}

		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = matchService.findAllCount(match, k);
		pager.setTotalCount(count);
		mav.addObject("list", matchService.findByPager(pager, k, "\"MatchStatus\", \"MatchOrderby\""));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "match");
		mav.addObject("status1", null == s1 ? "" : s1); // 列表页面中首先显示全部
		mav.setViewName("match/MatchList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param matchId
	 * @return
	 */
	@RequestMapping("edit/{matchId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "matchId") int matchId) {
		ModelAndView mav = new ModelAndView();
		if (0 == matchId) {
			match = new Match();
			match.setMatchId(matchId);
			match.setMatchOrderby(1);
		} else {
			match = matchService.findById(matchId);
		}
		mav.addObject("match", match);
		mav.addObject("active", "match");
		mav.setViewName("match/MatchEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param match
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("match") @Valid Match match, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("statusMap", getStatusMap());
			mav.addObject("match", match);
			mav.addObject("active", "match");
			mav.setViewName("match/MatchEdit");
			return mav;
		}

		String startDate = match.getMatchStartDate();
		String endDate = match.getMatchEndDate();
		if (0 < startDate.compareTo(endDate)) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("statusMap", getStatusMap());
			mav.addObject("match", match);
			mav.addObject("active", "match");
			mav.setViewName("match/MatchEdit");
			mav.addObject("tips", "开始时间不能大于结束时间！");
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				match.setMatchPicture(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}
		// （默认0即将开始；1进行中；2已结束）
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		if (-1 == compareDate(now, startDate)) {
			match.setMatchStatus(0);
		}
		if ((0 == compareDate(now, startDate) || 1 == compareDate(now, startDate)) && -1 == compareDate(now, endDate)) {
			match.setMatchStatus(1);
		}
		if (1 == compareDate(now, endDate) || 0 == compareDate(now, endDate)) {
			match.setMatchStatus(2);
		}

		if (0 == match.getMatchId()) {
			matchService.save(match);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			matchService.update(match);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", matchService.findAllList("", "\"MatchStatus\", \"MatchOrderby\""));
		map.put("request", request);
		map.put("cur", "match");
		generateHTML(map, request);
		String title = 0 == match.getMatchId() ? "新增赛事：" : "修改赛事：";
		logService.saveLog(request, "match", title + match.getMatchName());
		return new ModelAndView("redirect:/manage/match");
	}

	private static int compareDate(String startDate, String endDate) {
		int returnInt = 9;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = sdf.parse(startDate);
			Date dt2 = sdf.parse(endDate);
			if (dt1.getTime() > dt2.getTime()) {
				returnInt = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				returnInt = -1;
			} else {
				returnInt = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnInt;
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateHTML(Map<String, Object> map, final HttpServletRequest request) throws Exception {
		// 生成二级赛事页面
		FileUtil.generateHTML("WEB-INF/ftl", "match/Match.ftl", "match.html", map, request.getSession().getServletContext(),
				UPLOAD_PATH.substring(0, UPLOAD_PATH.lastIndexOf("upload")));

		// 生成首页的赛事日程
		FileUtil.generateHTML("WEB-INF/ftl/match", "MatchIndex.ftl", "index_match.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
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
