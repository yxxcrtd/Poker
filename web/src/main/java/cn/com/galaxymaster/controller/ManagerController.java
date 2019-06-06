package cn.com.galaxymaster.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.util.MD5;

/**
 * Manager Controller
 */
@RestController
@RequestMapping("manage/manager")
public class ManagerController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("k", k);
		k = null == k ? "" : "\"ManagerName\" like '%" + k.trim() + "%'";
		int count = managerService.findAllCount(manager, k);
		mav.addObject("list", managerService.findAllList(k, "\"ManagerId\""));
		mav.addObject("count", count);
		mav.addObject("active", "manager");
		mav.setViewName("manager/ManagerList");
		return mav;
	}

	/**
	 * New
	 * 
	 * @return
	 */
	@RequestMapping("edit/0")
	public ModelAndView edit() {
		return mav(new Manager(), "manager", "manager/ManagerEdit");
	}

	/**
	 * Find Manager By Name
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("find")
	public @ResponseBody String find(@RequestParam(value = "name", required = true) String name) {
		return managerService.getManagerByName(name);
	}

	/**
	 * Check
	 * 
	 * @return
	 */
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String check(@RequestParam(value = "obj", required = true) String obj, @RequestParam(value = "id", required = true) int id, HttpServletRequest request) {
		logService.saveLog(request, "manager", "修改管理员权限");
		String result = managerService.updateStatus(obj, id);
		return result;
	}

	/**
	 * Save
	 * 
	 * @param manager
	 * @param result
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("manager") @Valid Manager manager, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return mav(manager, "manager", "manager/ManagerEdit");
		}
		manager.setManagerPassword(MD5.toMD5(manager.getManagerPassword()));
		managerService.save(manager);
		redirectAttributes.addFlashAttribute("tips", "保存成功！");
		logService.saveLog(request, "manager", "新增管理员" + manager.getManagerName());
		return new ModelAndView("redirect:/manage/manager");
	}

	/**
	 * Delete
	 * 
	 * @param ids
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping("del/{ids}")
	public ModelAndView delete(@PathVariable(value = "ids") String ids, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		for (String s : ids.split(",")) {
			redirectAttributes.addFlashAttribute("tips", managerService.deleteById(Integer.valueOf(s)) ? "删除成功！" : "删除失败！");
		}
		return new ModelAndView("redirect:/manage/manager");
	}

	/**
	 * Reset
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping("reset/{id}")
	public ModelAndView reset(@PathVariable(value = "id") int id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("tips", managerService.reset(id) ? "密码重置成功（123456）！" : "密码重置失败！");
		return new ModelAndView("redirect:/manage/manager");
	}

	/**
	 * Update Passwrod
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping("updatePassword/{id:[\\d]+}")
	public ModelAndView updatePassword(@PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView();
		manager = managerService.findById(id);
		manager.setManagerPassword("");
		mav.addObject("active", "manager");
		mav.addObject("manager", manager);
		mav.setViewName("manager/ManagerUpdatePasswrod");
		return mav;
	}

	/**
	 * Update Passwrod Save
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping("updatePasswordSave")
	public ModelAndView updatePasswordSave(@ModelAttribute("manager") @Valid Manager manager, final RedirectAttributes redirectAttributes) {
		String newPassword = manager.getManagerPassword();
		if (0 < manager.getManagerId()) {
			manager = managerService.findById(manager.getManagerId());
		}
		manager.setManagerPassword(MD5.toMD5(newPassword));
		managerService.update(manager);
		redirectAttributes.addFlashAttribute("tips", "密码修改成功！");
		return new ModelAndView("redirect:/manage/manager");
	}

}
