package cn.com.galaxymaster.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cn.com.galaxymaster.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.util.Constants;

/**
 * Manage Controller
 */
@Controller
@RequestMapping("manage")
public class ManageController extends BaseController {

	/**
	 * Manage
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("active", "manage");
		mav.setViewName("Manage");

		System.out.println("================" + DateUtil.getNow());
		return mav;
	}

	/**
	 * Top
	 * 
	 * @return
	 */
	@RequestMapping(value = "top", method = RequestMethod.GET)
	public ModelAndView top(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", request.getSession().getAttribute(Constants.SESSION_USER_KEY));
		mav.setViewName("Top");
		return mav;
	}

	/**
	 * Menu
	 * 
	 * @return
	 */
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public ModelAndView menu(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", request.getSession().getAttribute(Constants.SESSION_USER_KEY));
		mav.setViewName("Menu");
		return mav;
	}

	/**
	 * Main
	 * 
	 * @return
	 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main() {
		return "Main";
	}

	/**
	 * Manage Login
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("manager") @Valid Manager manager, BindingResult result, HttpServletRequest request) {
//		if (result.hasErrors()) {
//			return mav(manager, "manager", "Login");
//		}
		return new ModelAndView("redirect:/manage");
	}

}
