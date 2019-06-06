package cn.com.galaxymaster.controller;

import java.io.IOException;
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

import cn.com.galaxymaster.domain.Game;
import cn.com.galaxymaster.domain.Picture;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import cn.com.galaxymaster.util.TaskExecutor;
import freemarker.template.TemplateException;

/**
 * picture Controller
 */
@Controller
@RequestMapping("manage/picture")
public class PictureController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "s", required = true, defaultValue = "") String s,
			@RequestParam(value = "g", required = true, defaultValue = "") String g, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("s", s);// 状态
		mav.addObject("g", g);// 游戏
		mav.addObject("k", k);// 查询字符

		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : like;
		/* 查询条件 */
		StringBuffer where = new StringBuffer();
		if (s != null && !"".equals(s.trim())) {
			where.append("\"PictureStatus\"=").append(s);
		}

		if (g != null && !"".equals(g.trim())) {
			if (where.length() > 2) {
				where.append(" AND ");
			}
			where.append("\"PictureGame\"=").append(g);
		}

		if (!StringUtils.isEmpty(k)) {
			if (where.length() > 2) {
				where.append(" AND ");
			}
			where.append("\"PictureAlt\" LIKE ").append(k);
		}

		pager = new Pager();
		pager.setPageNo(p);
		int count = pictureService.findAllCount(picture, where.toString());
		pager.setTotalCount(count);
		mav.addObject("list", pictureService.findByPager(pager, where.toString(), " \"PictureOrderby\" ASC"));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("gameMap", getGameMap());
		mav.addObject("active", "picture");
		mav.addObject("status", null == s ? "" : s); // 列表页面中首先显示全部
		mav.addObject("game", null == g ? "" : g); // 列表页面中首先显示全部
		/* 游戏名称 */
		mav.setViewName("picture/PictureList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param pictureId
	 * @return
	 */
	@RequestMapping("edit/{pictureId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "pictureId") int pictureId) {
		ModelAndView mav = new ModelAndView();
		if (0 == pictureId) {
			picture = new Picture();
			picture.setPictureId(0);
			picture.setPictureOrderby(1);
		} else {
			picture = pictureService.findById(pictureId);
		}
		mav.addObject("visibleMap", getVisibleMap());
		mav.addObject("gameMap", getGameMap());
		mav.addObject("picture", picture);
		mav.addObject("active", "picture");
		mav.setViewName("picture/PictureEdit");
		return mav;
	}

	private Map<String, String> getVisibleMap() {
		Map<String, String> visibleMap = new LinkedHashMap<String, String>();
		visibleMap.put("0", "显示");
		visibleMap.put("1", "隐藏");
		return visibleMap;
	}

	private Map<String, String> getGameMap() {
		List<Game> games = gameService.findAllList("", "");
		Map<String, String> map = new LinkedHashMap<String, String>();

		for (Game game : games) {
			map.put(game.getGameId() + "", game.getGameTitle());
		}
		return map;
	}

	/**
	 * Save
	 * 
	 * @param picture
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("picture") @Valid Picture picture, BindingResult result, final RedirectAttributes redirectAttributes,
			MultipartHttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			// for (ObjectError err : result.getAllErrors()) {
			// LOGGER.error(err.toString());
			// }
			// return new ModelAndView("redirect:/manage/picture/edit/0");

			ModelAndView mav = new ModelAndView();
			mav.addObject("active", "picture");
			mav.addObject("picture", picture);
			mav.addObject("visibleMap", getVisibleMap());
			mav.addObject("gameMap", getGameMap());
			mav.setViewName("picture/PictureEdit");
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				picture.setPictureUri(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		if (0 == picture.getPictureId()) {
			pictureService.save(picture);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			pictureService.update(picture);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}
		final ServletContext servletContext = request.getSession().getServletContext();
		String title = 0 == picture.getPictureId() ? "新增游戏图片：" : "修改游戏图片：";
		logService.saveLog(request, "picture", title + picture.getPictureAlt());
		generateHTML(servletContext);
		return new ModelAndView("redirect:/manage/picture");
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
			LOGGER.error("update picture Status Parameter Error");
			throw new Exception("update picture Status Parameter Error");
		} else {
			pictureService.updateStatus(ids, status);
			String s = "0".equals(status) ? "显示" : "隐藏";
			logService.saveLog(request, "picture", "更新游戏图片状态为：" + s);
		}
		generateHTML(request.getSession().getServletContext());
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateHTML(final ServletContext servletContext) throws Exception {
		// 修改新闻、广告、赛事、活动、棋牌教程时多线程更新游戏库/manage/game
		// final String url = HttpService.getInstance().getUrl(request,
		// "/updateHtml");
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
