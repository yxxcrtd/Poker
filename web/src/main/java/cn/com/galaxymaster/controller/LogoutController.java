package cn.com.galaxymaster.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.util.Constants;

/**
 * Logout
 */
@Controller
@RequestMapping("logout")
public class LogoutController extends BaseController {

	/**
	 * Logout
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if (null != manager) {
			logService.saveLog(request, "logout", manager.getManagerName() + "注销");
		}
		request.getSession().setAttribute(Constants.SESSION_USER_KEY, null);
		return new ModelAndView("Logout");
	}

}
