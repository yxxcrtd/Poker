package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.Seo;
import cn.com.galaxymaster.util.FileUtil;
import freemarker.template.TemplateException;

/**
 * SEO Controller
 */
@Controller
@RequestMapping("manage/seo")
public class SeoController extends BaseController {

	/**
	 * Edit
	 * 
	 * @param seoId
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView();
		seo = seoService.findById(1);
		if (null == seo) {
			seo = new Seo();
			seo.setSeoId(1);
		}
		mav.addObject("seo", seo);
		mav.setViewName("seo/SeoEdit");
		mav.addObject("active", "seo");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param seo
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("seo") @Valid Seo seo, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			return new ModelAndView("seo/SeoEdit", "seo", seo);
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				seo.setSeoIcon(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		if (0 == seo.getSeoId()) {
			seoService.save(seo);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			seoService.update(seo);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seo", seoService.findById(1));
		map.put("request", request);

		String title = 0 == seo.getSeoId() ? "新增SEO：" : "修改SEO";
		logService.saveLog(request, "seo", title);
		generateHTML(map, request);

		return new ModelAndView("redirect:/manage/seo");
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
		FileUtil.generateHTML("WEB-INF/ftl/seo", "Seo.ftl", "seo.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
	}

}
