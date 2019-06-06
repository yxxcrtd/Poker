package cn.com.galaxymaster.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class FileUtil extends BaseUtil {

	public static String uploadFile(MultipartFile file, String root, Boolean isOriginalName) throws Exception {
		String originalName = file.getOriginalFilename();
		StringBuffer fileName = new StringBuffer(isOriginalName ? originalName : String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase(), new Date()));
		String dest = new StringBuffer(root).append(fileName).toString();
		File destFile = new File(dest);
		if (!destFile.getParentFile().exists()) {
			if (!destFile.getParentFile().mkdirs()) {
				return "";
			}
		}
		file.transferTo(destFile);
		return fileName.toString();
	}

	@SuppressWarnings("deprecation")
	public static void generateHTML(String folderName, String ftl, String htmlName, Map<String, Object> map, ServletContext servletContext, String path) throws Exception {
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(servletContext, File.separator + folderName);
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		Template template = cfg.getTemplate(ftl);
		template.setEncoding("UTF-8");
		File pathFile = new File(path);
		if (!pathFile.exists()) {
			pathFile.mkdir();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + htmlName));
		File htmlFile = new File(path + htmlName);
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
		template.process(map, out);
		bufferedWriter.close();
		out.flush();
		out.close();
	}

}
