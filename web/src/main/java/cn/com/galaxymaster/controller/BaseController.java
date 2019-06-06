package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

import cn.com.galaxymaster.domain.Activity;
import cn.com.galaxymaster.domain.Ad;
import cn.com.galaxymaster.domain.Chess;
import cn.com.galaxymaster.domain.Game;
import cn.com.galaxymaster.domain.Link;
import cn.com.galaxymaster.domain.Log;
import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.domain.Match;
import cn.com.galaxymaster.domain.News;
import cn.com.galaxymaster.domain.Picture;
import cn.com.galaxymaster.domain.Seo;
import cn.com.galaxymaster.domain.Tutorial;
import cn.com.galaxymaster.domain.User;
import cn.com.galaxymaster.domain.Video;
import cn.com.galaxymaster.service.ActivityService;
import cn.com.galaxymaster.service.AdService;
import cn.com.galaxymaster.service.ChessService;
import cn.com.galaxymaster.service.GameService;
import cn.com.galaxymaster.service.LinkService;
import cn.com.galaxymaster.service.LogService;
import cn.com.galaxymaster.service.ManagerService;
import cn.com.galaxymaster.service.MatchService;
import cn.com.galaxymaster.service.NewsService;
import cn.com.galaxymaster.service.PictureService;
import cn.com.galaxymaster.service.SeoService;
import cn.com.galaxymaster.service.TutorialService;
import cn.com.galaxymaster.service.UserService;
import cn.com.galaxymaster.service.VideoService;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import freemarker.template.TemplateException;

/**
 * Base Controller
 */
public class BaseController {

	/** Log */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	@Value("${upload.path}")
	protected String UPLOAD_PATH;

	@Value("${code.path.java}")
	protected String CODE_PATH_JAVA;

	@Value("${code.path.ftl}")
	protected String CODE_PATH_FTL;

	/** Pager Object */
	protected Pager pager;

	/** User Object */
	protected User user;

	/** Link Object */
	protected Link link;

	/** News Object */
	protected News news;

	/** SEO Object */
	protected Seo seo;

	/** Ad Object */
	protected Ad ad;

	/** Match Object */
	protected Match match;

	/** Game Object */
	protected Game game;

	/** Activity Object */
	protected Activity activity;

	/** Video Object */
	protected Video video;

	/** Chess Object */
	protected Chess chess;

	/** Manager Object */
	protected Manager manager;

	/** Tutorial Object */
	protected Tutorial tutorial;

	/** Picture Object */
	protected Picture picture;

	/** Log Object */
	protected Log log;

	/** User Service */
	@Autowired
	protected UserService userService;

	/** Link Service */
	@Autowired
	protected LinkService linkService;

	/** SEO Service */
	@Autowired
	protected SeoService seoService;

	/** Ad Service */
	@Autowired
	protected NewsService newsService;

	/** Ad Service */
	@Autowired
	protected AdService adService;

	/** Match Service */
	@Autowired
	protected MatchService matchService;

	/** Game Service */
	@Autowired
	protected GameService gameService;

	/** Activity Service */
	@Autowired
	protected ActivityService activityService;

	/** Video Service */
	@Autowired
	protected VideoService videoService;

	/** Manager Service */
	@Autowired
	protected ManagerService managerService;

	/** Chess Service */
	@Autowired
	protected ChessService chessService;

	/** Tutorial Service */
	@Autowired
	protected TutorialService tutorialService;

	/** Picture Service */
	@Autowired
	protected PictureService pictureService;

	/** Log Service */
	@Autowired
	protected LogService logService;

	/** Return ModelAndView */
	protected ModelAndView mav(Object o, String s, String v) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(s, o);
		mav.addObject("active", s);
		mav.setViewName(v);
		return mav;
	}

	/** 获取状态 */
	protected Map<String, String> getStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "显示"); // 默认
		map.put("1", "不显示");
		return map;
	}

	/** 广告状态 */
	protected Map<String, String> getAdVisibleMap() {
		Map<String, String> visibleMap = new LinkedHashMap<String, String>();
		visibleMap.put("0", "显示");
		visibleMap.put("1", "隐藏");
		return visibleMap;
	}

	/** 广告位置 */
	protected Map<String, String> getAdLocationMap() {
		Map<String, String> locationMap = new LinkedHashMap<String, String>();
		locationMap.put("1", "首页");
		locationMap.put("2", "新闻");
		locationMap.put("3", "棋牌游戏");
		locationMap.put("4", "赛事专区");
		locationMap.put("5", "活动");
		locationMap.put("6", "视频");
		locationMap.put("7", "棋牌学堂");
		return locationMap;
	}

	/** 获取新闻分类 */
	protected Map<String, String> getNewsCategoryMap() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("0", "综合新闻"); // 默认
		map.put("1", "德州扑克");
		map.put("2", "斗地主");
		map.put("3", "麻将");
		map.put("4", "棋类");
		map.put("9", "其他");
		return map;
	}

	/** 获取新闻类型 */
	protected Map<String, String> getNewsTypeMap() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("0", "新闻"); // 默认
		map.put("1", "赛事");
		map.put("2", "公告");
		return map;
	}

	/** 获取棋牌教程分类 */
	protected Map<String, String> getTutorialCategoryMap() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("1", "德州扑克");
		map.put("2", "斗地主");
		map.put("3", "麻将");
		map.put("4", "棋类");
		map.put("9", "其他");
		return map;
	}

	/** 获取视频分类 */
	protected Map<String, String> getVideoCategoryMap() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("0", "赛事直播");
		map.put("1", "赛事回放");
		map.put("2", "专题栏目");
		map.put("3", "德州扑克");
		map.put("4", "中国象棋");
		map.put("5", "斗地主");
		map.put("6", "其他");
		return map;
	}

	/** 获取视频质量 */
	protected Map<String, String> getVideoQualityMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "蓝光");
		map.put("1", "超清");
		map.put("2", "高清");
		map.put("3", "标清");
		return map;
	}

	/** 活动状态 */
	protected Map<String, String> getActivityStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "准备"); // 默认
		map.put("1", "进行中");
		map.put("2", "已结束");
		return map;
	}

	/** 活动分类 */
	protected Map<String, String> getActivityCategoryMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "牌类"); // 默认
		map.put("1", "棋类");
		map.put("2", "麻将");
		return map;
	}

	/**
	 * refresh GameHTML File
	 * 
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void refreshGameHTML(final ServletContext servletContext) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", gameService.findAllList("\"GameStatus\" = 0", "\"GameOrderby\" ASC"));
		map.put("request", servletContext);
		map.put("cur", "game");
		// 生成棋牌规则
		FileUtil.generateHTML("WEB-INF/ftl/game", "game_class.ftl", "class_game.html", map, servletContext, UPLOAD_PATH);
		FileUtil.generateHTML("WEB-INF/ftl/game", "game_rule.ftl", "game_rule.html", map, servletContext, UPLOAD_PATH);
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
		FileUtil.generateHTML("WEB-INF/ftl/game", "game_index.ftl", "game.html", map, servletContext, UPLOAD_PATH.substring(0, UPLOAD_PATH.lastIndexOf("upload")));
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
			FileUtil.generateHTML("WEB-INF/ftl/game", "game_detail.ftl", "game" + game.getGameId() + ".html", map, servletContext, UPLOAD_PATH);
			// 棋牌规则详情页
			FileUtil.generateHTML("WEB-INF/ftl/game", "game_rule_detail.ftl", "gamerule" + game.getGameId() + ".html", map, servletContext, UPLOAD_PATH);

		}
		// 首页-快速入口
		FileUtil.generateHTML("WEB-INF/ftl/game", "index_game.ftl", "index_game.html", map, servletContext, UPLOAD_PATH);
		// 生成游戏产品页(其他产品)
		List<Game> game_other_list = gameService.findAllList("\"GameStatus\" = 0 AND \"GameType\" = 1", "\"GameUpdateTime\" DESC");
		for (Game game : game_other_list) {
			map.put("game", game);// 游戏
			map.put("pic_list", pictureService.findAllList("\"PictureStatus\" = 0 AND \"PictureGame\"=" + game.getGameId(), " \"PictureOrderby\" ASC"));
			FileUtil.generateHTML("WEB-INF/ftl/game", "game_other.ftl", "game" + game.getGameId() + ".html", map, servletContext, UPLOAD_PATH);
		}
	}

}
