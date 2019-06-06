package cn.com.galaxymaster.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.galaxymaster.domain.Activity;
import cn.com.galaxymaster.domain.Game;
import cn.com.galaxymaster.domain.Match;
import cn.com.galaxymaster.domain.News;
import cn.com.galaxymaster.domain.Search;
import cn.com.galaxymaster.domain.Video;

/**
 * Search
 */
@Controller
@RequestMapping("search")
public class SearchController extends BaseController {

	/**
	 * Search
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, @RequestParam(value = "k", required = false) String k) {
		List<Search> list = new ArrayList<>();
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		
		List<News> newsList = newsService.findAllList("\"NewsTitle\" LIKE " + like, "");
		LOGGER.info("新闻的搜索记录数：" + newsList.size());
		for (News obj : newsList) {
			Search search = new Search();
			search.setObj("news");
			search.setId(obj.getNewsId());
			search.setTitle(obj.getNewsTitle());
			search.setSummary(obj.getNewsSummary());
			search.setTime2(obj.getNewsCreateTime());
			search.setPicture(obj.getNewsPicture());
			list.add(search);
		}
		
		List<Match> matchList = matchService.findAllList("\"MatchName\" LIKE " + like, "");
		LOGGER.info("赛事的搜索记录数：" + matchList.size());
		for (Match obj : matchList) {
			Search search = new Search();
			search.setObj("match");
			search.setId(obj.getMatchId());
			search.setTitle(obj.getMatchName());
			search.setSummary(obj.getMatchSummary());
			search.setTime1(obj.getMatchStartDate() + " - " + obj.getMatchEndDate());
			search.setPicture(obj.getMatchPicture());
			list.add(search);
		}
		
		List<Video> videoList = videoService.findAllList("\"VideoTitle\" LIKE " + like, "\"VideoStatus\" = 0");
		LOGGER.info("视频的搜索记录数：" + videoList.size());
		for (Video obj : videoList) {
			Search search = new Search();
			search.setObj("video");
			search.setId(obj.getVideoId());
			search.setTitle(obj.getVideoTitle());
			search.setSummary(obj.getVideoSummary());
			search.setTime2(obj.getVideoCreateTime());
			search.setPicture(obj.getVideoPicture());
			list.add(search);
		}
		
		List<Game> gameList = gameService.findAllList("\"GameTitle\" LIKE " + like, "\"GameStatus\" = 0");
		LOGGER.info("游戏的搜索记录数：" + gameList.size());
		for (Game obj : gameList) {
			Search search = new Search();
			search.setObj("game");
			search.setId(obj.getGameId());
			search.setTitle(obj.getGameTitle());
			search.setSummary(obj.getGameRule());
			search.setTime2(obj.getGameUpdateTime());
			search.setPicture(obj.getGamePicture());
			list.add(search);
		}
		
		List<Activity> activityList = activityService.findAllList("\"ActivityTitle\" LIKE " + like, "");
		LOGGER.info("活动的搜索记录数：" + activityList.size());
		for (Activity obj : activityList) {
			Search search = new Search();
			search.setObj("action");
			search.setId(obj.getActivityId());
			search.setTitle(obj.getActivityTitle());
			search.setSummary(obj.getActivityBasicRules());
			search.setTime2(obj.getActivityCreateTime());
			search.setPicture(obj.getActivityPicture());
			list.add(search);
		}
		
		mav.addObject("list", list);
		mav.setViewName("Search");
		return mav;
	}

}
