package cn.com.galaxymaster.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.galaxymaster.domain.Activity;
import cn.com.galaxymaster.util.FileUtil;

/**
 * View Controller
 */
@RestController
@RequestMapping("view")
public class ViewController extends BaseController {

	/**
	 * View
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request, @RequestParam(value = "obj", required = true) String obj, @RequestParam(value = "id", required = true) int id) throws Exception {
		if ("video".equals(obj)) {
			videoService.increase(id);
		} else if ("news".equals(obj)) {
			newsService.increase(id);
		} else if ("manual".equals(obj)) {
			chessService.increase(id);
		}else if ("tutorial".equals(obj)) {
			tutorialService.increase(id);
		} else if ("activity".equals(obj)) {
			activityService.increase(id);
			// 生成二级活动页面
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cur", "activity");
			List<Activity> list = activityService.findAllList("\"ActivityStatus\" > 0", "\"ActivityCreateTime\" DESC");
			map.put("list", list);
			// 生成二级页面的牌类
			map.put("category_0_list", activityService.findAllList("\"ActivityCategory\" = 0 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC"));
			// 生成二级页面的棋类
			map.put("category_1_list", activityService.findAllList("\"ActivityCategory\" = 1 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC"));
			// 生成二级页面的麻将
			map.put("category_2_list", activityService.findAllList("\"ActivityCategory\" = 2 AND \"ActivityStatus\" > 0", "\"ActivityOrderby\", \"ActivityCreateTime\" DESC"));

			FileUtil.generateHTML("WEB-INF/ftl", "activity/Activity.ftl", "action.html", map, request.getSession().getServletContext(),
					UPLOAD_PATH.substring(0, UPLOAD_PATH.lastIndexOf("upload")));
		}
		return "success";
	}

}
