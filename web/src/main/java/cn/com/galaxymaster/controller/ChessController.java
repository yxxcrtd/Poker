package cn.com.galaxymaster.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.galaxymaster.domain.Chess;
import cn.com.galaxymaster.util.FileUtil;
import cn.com.galaxymaster.util.Pager;
import freemarker.template.TemplateException;

/**
 * Chess Controller
 */
@Controller
@RequestMapping("manage/chess")
public class ChessController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p, @RequestParam(value = "s", required = true, defaultValue = "") String s,
			@RequestParam(value = "k", required = false) String k) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("s", s);// 游戏状态
		mav.addObject("k", k);// 查询字符

		String like = "%".equals(k) ? "'%\\%%'" : "'%" + k + "%'";
		k = null == k ? "" : like;
		/* 查询条件 */
		StringBuffer where = new StringBuffer();
		if (s != null && !"".equals(s.trim())) {
			where.append("\"ChessStatus\"=").append(s);
		}

		if (!StringUtils.isEmpty(k)) {
			if (where.length() > 2) {
				where.append(" AND ");
			}
			where.append("\"ChessTitle\" LIKE ").append(k);
		}

		pager = new Pager();
		pager.setPageNo(p);
		int count = chessService.findAllCount(chess, where.toString());
		pager.setTotalCount(count);
		mav.addObject("list", chessService.findByPager(pager, where.toString(), " \"ChessOrderby\" ASC, \"ChessId\" DESC"));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.addObject("active", "chess");
		mav.addObject("status", null == s ? "" : s); // 列表页面中首先显示全部
		mav.setViewName("chess/ChessList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param chessId
	 * @return
	 */
	@RequestMapping("edit/{chessId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "chessId") int chessId) {
		ModelAndView mav = new ModelAndView();
		if (0 == chessId) {
			chess = new Chess();
			chess.setChessId(0);
			chess.setChessOrderby(1);
			chess.setChessProcess("0");
		} else {
			chess = chessService.findById(chessId);
		}
		mav.addObject("visibleMap", getVisibleMap());
		mav.addObject("resultMap", getResultMap());
		mav.addObject("chess", chess);
		mav.setViewName("chess/ChessEdit");
		mav.addObject("active", "chess");
		return mav;
	}

	private Map<String, String> getVisibleMap() {
		Map<String, String> visibleMap = new LinkedHashMap<String, String>();
		visibleMap.put("0", "显示");
		visibleMap.put("1", "隐藏");
		return visibleMap;
	}

	private Map<String, String> getResultMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "红方胜");
		map.put("1", "黑方胜");
		return map;
	}

	/**
	 * Save
	 * 
	 * @param chess
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("chess") @Valid Chess chess, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			// for (ObjectError err : result.getAllErrors()) {
			// LOGGER.error(err.toString());
			// }
			// return new ModelAndView("redirect:/manage/chess/edit/0");

			ModelAndView mav = new ModelAndView();
			mav.addObject("active", "chess");
			mav.addObject("operation", "save");
			mav.addObject("chess", chess);
			mav.addObject("visibleMap", getVisibleMap());
			mav.addObject("resultMap", getResultMap());
			mav.setViewName("chess/ChessEdit");
			return mav;
		}

		List<MultipartFile> reds = request.getFiles("red");
		for (MultipartFile file : reds) {
			if (!file.isEmpty()) {
				chess.setChessRedPic(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}
		List<MultipartFile> blacks = request.getFiles("black");
		for (MultipartFile file : blacks) {
			if (!file.isEmpty()) {
				chess.setChessBlackPic(FileUtil.uploadFile(file, UPLOAD_PATH, false));
			}
		}

		if (0 == chess.getChessId()) {
			chessService.save(chess);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			chessService.update(chess);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}
		String title = 0 == chess.getChessId() ? "新增棋谱：" : "修改棋谱：";
		logService.saveLog(request, "chess", title + chess.getChessTitle());
		generateHTML(request);

		return new ModelAndView("redirect:/manage/chess");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public @ResponseBody void updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 请求参数:id数组、状态码
		String[] ids = request.getParameterValues("idList[]");
		String status = request.getParameter("status");
		if (ids == null || ids.length == 0 || StringUtils.isEmpty(ids[0]) || StringUtils.isEmpty(status)) {
			LOGGER.error("update Chess Status Parameter Error");
			throw new Exception("update Chess Status Parameter Error");
		} else {
			chessService.updateStatus(ids, status);
			String s = "0".equals(status) ? "显示" : "隐藏";
			logService.saveLog(request, "chess", "更新棋谱状态为：" + s);
		}
		generateHTML(request);
	}

	/**
	 * Generate HTML File
	 * 
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("unchecked")
	protected void generateHTML(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("request", request);

		map.put("list", chessService.findAllList("\"ChessStatus\"=0", "\"ChessId\" DESC"));
		map.put("y_hit", chessService.findAllList("\"ChessStatus\"=0 AND EXTRACT(MONTH from \"ChessCreateTime\")=EXTRACT(MONTH from now())", "\"ChessCreateTime\" DESC"));
		map.put("z_hit", chessService.findAllList("\"ChessStatus\"=0 AND EXTRACT(WEEK from \"ChessCreateTime\")=EXTRACT(WEEK from now())", "\"ChessCreateTime\" DESC"));

		map.put("cur", "class");

		// 生成二级页面 - 关注排行
		map.put("hotChess", chessService.findAllList("\"ChessStatus\" = 0", "\"ChessHit\",\"ChessCreateTime\" DESC"));

		// 生成棋牌学堂部分
		FileUtil.generateHTML("WEB-INF/ftl/chess", "chess_class.ftl", "class_manual.html", map, request.getSession().getServletContext(), UPLOAD_PATH);

		// 生成棋谱详情 页面
		List<Chess> list = (List<Chess>) map.get("list");
		for (int i = 0; i < list.size(); i++) {
			map.put("chess", list.get(i));
			FileUtil.generateHTML("WEB-INF/ftl/chess", "chess_manual.ftl", "manual" + list.get(i).getChessId() + ".html", map, request.getSession().getServletContext(),
					UPLOAD_PATH);
		}
		// 生成棋谱 列表
		FileUtil.generateHTML("WEB-INF/ftl/chess", "chess_list.ftl", "manual_list.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
	}

}
