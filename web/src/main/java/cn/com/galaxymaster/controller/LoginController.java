package cn.com.galaxymaster.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.Log;
import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.util.Constants;
import cn.com.galaxymaster.util.MD5;

/**
 * Login
 */
@Controller
@RequestMapping("login")
public class LoginController extends BaseController {

	/**
	 * Login
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("manager", new Manager());
		mav.setViewName("Login");
		return mav;
	}

	/**
	 * Login Check
	 */
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public ModelAndView check(@ModelAttribute("manager") @Valid Manager manager, BindingResult result, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			if (null == manager.getManagerName() || "".equals(manager.getManagerName())) {
				redirectAttributes.addFlashAttribute("tips", "用户名不能为空！");
				return new ModelAndView("redirect:/login");
			}
			if (null == manager.getManagerPassword() || "".equals(manager.getManagerPassword())) {
				redirectAttributes.addFlashAttribute("tips", "密码不能为空！");
				return new ModelAndView("redirect:/login");
			}
		}
		
		Manager user = managerService.findByName(manager.getManagerName());
		if (null != user) {
			if (!MD5.toMD5(manager.getManagerPassword()).equals(user.getManagerPassword())) {
				redirectAttributes.addFlashAttribute("tips", "用户名或密码不正确！");
				return new ModelAndView("redirect:/login");
			} else {
				request.getSession().setAttribute(Constants.SESSION_USER_KEY, user);
				log = new Log();
				log.setLogObj("login");
				log.setLogTitle("登录");
				log.setLogCreateTime(new Date());
				log.setLogUser(user.getManagerName());
				log.setLogIP(request.getRemoteAddr());
				logService.save(log);
			}
		}
		return new ModelAndView("redirect:/manage");
	}

}
