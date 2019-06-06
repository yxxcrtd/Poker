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

import cn.com.galaxymaster.domain.Video;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import cn.com.galaxymaster.util.TaskExecutor;
import freemarker.template.TemplateException;

/**
 * Video Controller
 */
@Controller
@RequestMapping("manage/video")
public class VideoController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "s1", required = false) String s1,
			@RequestParam(value = "s2", required = false) String s2, @RequestParam(value = "s3", required = false) String s3, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : "\"VideoTitle\" like " + like;

		mav.addObject("s1", s1);
		if (null != s1 && !"".equals(s1)) {
			k += " AND \"VideoCategory\" = " + s1;
		}
		mav.addObject("s2", s2);
		if (null != s2 && !"".equals(s2)) {
			k += " AND \"VideoQuality\" = " + s2;
		}
		mav.addObject("s3", s3);
		if (null != s3 && !"".equals(s3)) {
			k += " AND \"VideoStatus\" = " + s3;
		}

		pager = new Pager();
		pager.setPageNo(p);
		int count = videoService.findAllCount(video, k);
		pager.setTotalCount(count);
		mav.addObject("list", videoService.findByPager(pager, k, "\"VideoStatus\", \"VideoOrderby\""));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "video");
		mav.setViewName("video/VideoList");
		mav.addObject("status1", null == s1 ? "" : s1);
		mav.addObject("status2", null == s2 ? "" : s2);
		mav.addObject("status3", null == s3 ? "" : s3);
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param videoId
	 * @return
	 */
	@RequestMapping("edit/{videoId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "videoId") int videoId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("statusMap", getStatusMap());
		mav.addObject("categoryMap", getVideoCategoryMap());
		mav.addObject("qualityMap", getVideoQualityMap());
		if (0 == videoId) {
			video = new Video();
			video.setVideoId(videoId);
			video.setVideoOrderby(1);
			video.setVideoHit(0);
			video.setVideoStatus(0);
		} else {
			video = videoService.findById(videoId);
		}
		mav.addObject("video", video);
		mav.addObject("active", "video");
		mav.setViewName("video/VideoEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param video
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("video") @Valid Video video, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("statusMap", getStatusMap());
			mav.addObject("categoryMap", getVideoCategoryMap());
			mav.addObject("qualityMap", getVideoQualityMap());
			mav.addObject("video", video);
			mav.addObject("active", "video");
			mav.setViewName("video/VideoEdit");
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				video.setVideoPicture(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		if (0 == video.getVideoId()) {
			video.setVideoCreateTime(new Date());
			videoService.save(video);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			videoService.update(video);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}
		String title = 0 == video.getVideoId() ? "新增视频：" : "修改视频";
		logService.saveLog(request, "video", title + video.getVideoTitle());
		generateHTML(request);
		return new ModelAndView("redirect:/manage/video");
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
		videoService.updateStatus(ids, status);
		String s = "0".equals(status) ? "显示" : "隐藏";
		logService.saveLog(request, "video", "更新视频状态为：" + s);
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
	protected void generateHTML(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("request", request);
		map.put("cur", "video");

		// 生成首页上的视频部分 - 0 赛事直播
		map.put("category_0_list", videoService.findAllList("\"VideoCategory\" = 0 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC"));
		// 生成首页上的视频部分 - 1 赛事回放
		map.put("category_1_list", videoService.findAllList("\"VideoCategory\" = 1 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC"));
		// 生成首页上的视频部分 - 2 专题栏目
		map.put("category_2_list", videoService.findAllList("\"VideoCategory\" = 2 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC"));
		FileUtil.generateHTML("WEB-INF/ftl", "video/VideoIndex.ftl", "index_video.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		// 生成首页上的视频部分 - 3 视频教学 -德州扑克
		map.put("category_3_list", videoService.findAllList("\"VideoCategory\" = 3 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC"));
		// 生成首页上的视频部分 - 4 视频教学 -中国象棋
		map.put("category_4_list", videoService.findAllList("\"VideoCategory\" = 4 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC"));
		// 生成首页上的视频部分 - 5 视频教学 -斗地主
		map.put("category_5_list", videoService.findAllList("\"VideoCategory\" = 5 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC"));
		// 生成首页上的视频部分 - 6 视频教学-其他
		map.put("category_6_list", videoService.findAllList("\"VideoCategory\" = 6 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC"));

		// 生成详情页 TODO 以后单独生成
		List<Video> list = videoService.findAllList("\"VideoStatus\" = 0", "\"VideoCreateTime\" DESC");
		map.put("list", list);

		// 二级页面
		FileUtil.generateHTML("WEB-INF/ftl", "video/Video.ftl", "video.html", map, request.getSession().getServletContext(),
				UPLOAD_PATH.substring(0, UPLOAD_PATH.lastIndexOf("upload")));
		for (Video video : list) {
			map.put("video", video);
			FileUtil.generateHTML("WEB-INF/ftl", "video/VideoDetail.ftl", "video" + video.getVideoId() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		}
		FileUtil.generateHTML("WEB-INF/ftl", "video/VideoList1.ftl", "video_list1.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

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
