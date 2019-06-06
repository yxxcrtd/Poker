package cn.com.galaxymaster.util;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 在 Freemarker 模板中过滤 HTML 标签
 */
@SuppressWarnings({ "rawtypes", "deprecation" })
public class FreemarkerFilterHTML implements TemplateMethodModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateMethodModel#exec(java.util.List)
	 */
	@Override
	public Object exec(List args) throws TemplateModelException {
		String s = "";
		if (null != args && 0 < args.size()) {
			s = (String) args.get(0);

			// 将所有以<开头的，以>结尾的都过滤掉 | 
			s = s.replaceAll("<([^>]*)>", "").replaceAll("[\\t\\n\\r]", "").replace(" ", "");
		}
		return s;
	}

}
