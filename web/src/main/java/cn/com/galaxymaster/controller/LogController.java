package cn.com.galaxymaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.galaxymaster.util.Pager;

/**
 * Log Controller
 */
@Controller
@RequestMapping("manage/log")
public class LogController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "manager", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : "\"LogTitle\" like " + like;
		
		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = logService.findAllCount(log, k);
		pager.setTotalCount(count);
		mav.addObject("list", logService.findByPager(pager, k, "\"LogCreateTime\" DESC"));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "log");
		mav.setViewName("log/LogList");
		return mav;
	}

}
