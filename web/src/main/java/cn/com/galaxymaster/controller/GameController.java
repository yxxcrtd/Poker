package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import cn.com.galaxymaster.domain.News;
import cn.com.galaxymaster.domain.Tutorial;
import cn.com.galaxymaster.domain.Video;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import freemarker.template.TemplateException;

/**
 * game Controller
 */
@Controller
@RequestMapping("manage/game")
public class GameController extends BaseController {
	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "s", required = true, defaultValue = "2") int s,
			@RequestParam(value = "c", required = true, defaultValue = "0") int c, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("category", c);// 游戏分类
		mav.addObject("status", s);// 游戏状态
		mav.addObject("k", k);// 查询字符

		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : like;
		/* 查询条件 */
		StringBuffer where = new StringBuffer();
		if (s != 2) {
			where.append("\"GameStatus\"=").append(s);
		}
		if (c != 0) {
			if (where.length() > 2) {
				where.append(" AND ");
			}
			where.append("\"GameCategoryId\" =").append(c);
		}
		if (!StringUtils.isEmpty(k)) {
			if (where.length() > 2) {
				where.append(" AND ");
			}

			where.append("\"GameTitle\" LIKE ").append(k);
		}

		pager = new Pager();
		pager.setPageNo(p);
		int count = gameService.findAllCount(game, where.toString());
		pager.setTotalCount(count);
		mav.addObject("list", gameService.findByPager(pager, where.toString(), " \"GameOrderby\" ASC"));
		mav.addObject("categoryMap", getCategoryMap());
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "game");
		mav.setViewName("game/GameList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param gameId
	 * @return
	 */
	@RequestMapping("edit/{gameId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "gameId") int gameId) {
		ModelAndView mav = new ModelAndView();
		if (0 == gameId) {
			game = new Game();
			game.setGameId(gameId);
		} else {
			game = gameService.findById(gameId);
		}
		mav.addObject("visibleMap", getVisibleMap());
		mav.addObject("categoryMap", getCategoryMap());
		mav.addObject("typeMap", getGameTypeMap());
		mav.addObject("game", game);
		mav.setViewName("game/GameEdit");
		mav.addObject("active", "game");
		return mav;
	}

	private Object getGameTypeMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "8大单品");
		map.put("1", "其他产品");
		return map;
	}

	private Map<String, String> getVisibleMap() {
		Map<String, String> visibleMap = new LinkedHashMap<String, String>();
		visibleMap.put("0", "显示");
		visibleMap.put("1", "隐藏");
		return visibleMap;
	}

	private Map<String, String> getCategoryMap() {
		Map<String, String> locationMap = new LinkedHashMap<String, String>();
		locationMap.put("1", "棋类");
		locationMap.put("2", "牌类");
		locationMap.put("3", "麻将");
		locationMap.put("9", "其他");
		return locationMap;
	}

	/**
	 * Save
	 * 
	 * @param game
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("game") @Valid Game game, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			for (ObjectError err : result.getAllErrors()) {
				LOGGER.error(err.toString());
			}
			ModelAndView mav = new ModelAndView();
			mav.addObject("visibleMap", getVisibleMap());
			mav.addObject("categoryMap", getCategoryMap());
			mav.setViewName("game/GameEdit");
			mav.addObject("game", game);
			mav.addObject("active", "game");
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				game.setGamePicture(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}
		game.setGameUpdateTime(new Date());
		String title = "";
		if (0 == game.getGameId()) {
			gameService.save(game);
			title = "新增游戏：" + game.getGameTitle();
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			game.setGameUpdateTime(new Date());
			gameService.update(game);
			title = "修改游戏:" + game.getGameTitle();
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		generateHTML(request);
		logService.saveLog(request, "game", title);
		return new ModelAndView("redirect:/manage/game");
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
			LOGGER.error("update Game Status Parameter Error");
			throw new Exception("update Game Status Parameter Error");
		} else {
			gameService.updateStatus(ids, status);
			String s = "0".equals(status) ? "显示" : "隐藏";
			logService.saveLog(request, "game", "更新游戏状态为："+s);
			generateHTML(request);
		}
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

		map.put("list", gameService.findAllList("\"GameStatus\" = 0", "\"GameOrderby\" ASC"));
		map.put("request", request);
		map.put("cur", "game");
		// 生成棋牌规则
		FileUtil.generateHTML("WEB-INF/ftl/game", "game_class.ftl", "class_game.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		FileUtil.generateHTML("WEB-INF/ftl/game", "game_rule.ftl", "game_rule.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		// 游戏广告
		map.put("adList", adService.findAllList("\"AdLocation\" = 3 AND \"AdVisible\" = 0", "\"AdOrderby\""));
		// 生成游戏库首页
		map.put("game_comm_list", gameService.findAllList("\"GameStatus\" = 0 AND \"GameType\" = 0", "\"GameOrderby\" ASC"));
		// 生成游戏棋类
		map.put("game_1_list", gameService.findAllList("\"GameStatus\" = 0 AND \"GameCategoryId\" = 1", "\"GameOrderby\" ASC"));
		// 生成游戏牌类
		map.put("game_2_list", gameService.findAllList("\"GameStatus\" = 0 AND \"GameCategoryId\" = 2", "\"GameOrderby\" ASC"));
		// 生成游戏麻将
		map.put("game_3_list", gameService.findAllList("\"GameStatus\" = 0 AND \"GameCategoryId\" = 3", "\"GameOrderby\" ASC"));
		// 生成游戏其他
		map.put("game_9_list", gameService.findAllList("\"GameStatus\" = 0 AND \"GameCategoryId\" = 9", "\"GameOrderby\" ASC"));
		FileUtil.generateHTML("WEB-INF/ftl/game", "game_index.ftl", "game.html", map, request.getSession().getServletContext(),
				UPLOAD_PATH.substring(0, UPLOAD_PATH.lastIndexOf("upload")));
		// 生成游戏产品页(8大单品)
		List<Game> game_detail_list = gameService.findAllList("\"GameStatus\" = 0 AND \"GameType\" = 0", "\"GameUpdateTime\" DESC");
		for (Game game : game_detail_list) {
			map.put("game", game);// 游戏
			map.put("pic_list", pictureService.findAllList("\"PictureStatus\" = 0 AND \"PictureGame\"=" + game.getGameId(), " \"PictureOrderby\" ASC"));
			// 生成游戏新闻
			List<News> news_list = new ArrayList<News>();
			List<Activity> action_list = new ArrayList<Activity>();
			List<Tutorial> tutorial_list = new ArrayList<Tutorial>();
			List<Video> video_list = new ArrayList<Video>();
			if (game.getGameId() == 1) {// 德州扑克
				news_list = newsService.findAllList("\"NewsCategory\" = 1 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC");
				action_list = activityService.findAllList("\"ActivityCategory\" = 0 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC");
				tutorial_list = tutorialService.findAllList("\"TutorialCategory\" = 1 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC");
				video_list = videoService.findAllList("\"VideoCategory\" = 3 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC");
			} else if (game.getGameId() == 2) {// 斗地主
				news_list = newsService.findAllList("\"NewsCategory\" = 2 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC");
				action_list = activityService.findAllList("\"ActivityCategory\" = 0 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC");
				tutorial_list = tutorialService.findAllList("\"TutorialCategory\" = 2 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC");
				video_list = videoService.findAllList("\"VideoCategory\" = 5 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC");
			} else if (game.getGameId() == 3) {// 象棋
				news_list = newsService.findAllList("\"NewsCategory\" = 4 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC");
				action_list = activityService.findAllList("\"ActivityCategory\" = 1 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC");
				tutorial_list = tutorialService.findAllList("\"TutorialCategory\" = 4 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC");
				video_list = videoService.findAllList("\"VideoCategory\" = 4 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC");
			} else if (game.getGameCategoryId() == 3) {// 麻将
				news_list = newsService.findAllList("\"NewsCategory\" = " + game.getGameCategoryId() + " AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC");
				action_list = activityService.findAllList("\"ActivityCategory\" = 2 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC");
				tutorial_list = tutorialService.findAllList("\"TutorialCategory\" = 3 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC");
				video_list = videoService.findAllList("\"VideoCategory\" = 6 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC");
			} else if (game.getGameCategoryId() == 1) {// 棋类
				news_list = newsService.findAllList("\"NewsCategory\" = 9 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC");
				action_list = activityService.findAllList("\"ActivityCategory\" = 1 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC");
				tutorial_list = tutorialService.findAllList("\"TutorialCategory\" = 4 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC");
				video_list = videoService.findAllList("\"VideoCategory\" = 6 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC");
			} else {// 其他
				news_list = newsService.findAllList("\"NewsCategory\" = 9 AND \"NewsStatus\" = 0", "\"NewsOrderby\", \"NewsCreateTime\" DESC");
				action_list = activityService.findAllList("\"ActivityCategory\" = 0 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC");
				tutorial_list = tutorialService.findAllList("\"TutorialCategory\" = 9 AND \"TutorialStatus\" = 0", "\"TutorialOrderby\", \"TutorialCreateTime\" DESC");
				video_list = videoService.findAllList("\"VideoCategory\" = 6 AND \"VideoStatus\" = 0", "\"VideoOrderby\", \"VideoCreateTime\" DESC");
			}
			map.put("news_list", news_list);
			// 生成游戏赛事
			map.put("match_list", matchService.findAllList("", "\"MatchStatus\", \"MatchOrderby\""));
			// 生成游戏活动
			map.put("action_list", action_list);
			// 生成游戏棋牌教程
			map.put("tutorial_list", tutorial_list);
			// 生成游戏视频
			map.put("video_list", video_list);
			FileUtil.generateHTML("WEB-INF/ftl/game", "game_detail.ftl", "game" + game.getGameId() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
			// 棋牌规则详情页
			FileUtil.generateHTML("WEB-INF/ftl/game", "game_rule_detail.ftl", "gamerule" + game.getGameId() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		}
		// 首页-快速入口
		FileUtil.generateHTML("WEB-INF/ftl/game", "index_game.ftl", "index_game.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		// 生成游戏产品页(其他产品)
		List<Game> game_other_list = gameService.findAllList("\"GameStatus\" = 0 AND \"GameType\" = 1", "\"GameUpdateTime\" DESC");
		for (Game game : game_other_list) {
			map.put("game", game);// 游戏
			map.put("pic_list", pictureService.findAllList("\"PictureStatus\" = 0 AND \"PictureGame\"=" + game.getGameId(), " \"PictureOrderby\" ASC"));
			FileUtil.generateHTML("WEB-INF/ftl/game", "game_other.ftl", "game" + game.getGameId() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH);
		}
	}

}
