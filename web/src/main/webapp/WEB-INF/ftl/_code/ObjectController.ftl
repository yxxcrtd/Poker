package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.${obj};
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import freemarker.template.TemplateException;

/**
 * ${obj} Controller
 */
@Controller
@RequestMapping("manage/${obj?uncap_first}")
public class ${obj}Controller extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = ${obj?uncap_first}Service.findAllCount(${obj?uncap_first}, "");
		pager.setTotalCount(count);
		mav.addObject("list", ${obj?uncap_first}Service.findByPager(pager, "", "\"${obj}Location\", \"${obj}Orderby\""));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "${obj?uncap_first}");
		mav.setViewName("${obj?uncap_first}/${obj}List");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param ${obj?uncap_first}Id
	 * @return
	 */
	@RequestMapping("edit/{${obj?uncap_first}Id:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "${obj?uncap_first}Id") int ${obj?uncap_first}Id) {
		if (0 == ${obj?uncap_first}Id) {
			${obj?uncap_first} = new ${obj}();
			${obj?uncap_first}.set${obj}Id(${obj?uncap_first}Id);
		} else {
			${obj?uncap_first} = ${obj?uncap_first}Service.findById(${obj?uncap_first}Id);
		}
		return new ModelAndView("${obj?uncap_first}/${obj}Edit", "${obj?uncap_first}", ${obj?uncap_first});
	}

	/**
	 * Save
	 * 
	 * @param ${obj?uncap_first}
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("${obj?uncap_first}") @Valid ${obj} ${obj?uncap_first}, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("${obj?uncap_first}/${obj}Edit", "${obj?uncap_first}", ${obj?uncap_first});
		}

		if (0 == ${obj?uncap_first}.get${obj}Id()) {
			${obj?uncap_first}Service.save(${obj?uncap_first});
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			${obj?uncap_first}Service.update(${obj?uncap_first});
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("${obj?uncap_first}List", ${obj?uncap_first}Service.findAllList("", "${obj}Orderby"));
		map.put("request", request);
		try {
			generateHTML(map, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/manage/${obj?uncap_first}");
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
		FileUtil.generateHTML("WEB-INF/ftl/${obj?uncap_first}", "${obj}_zh_CN.ftl", "${obj?uncap_first}_zh_CN.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
	}

}
