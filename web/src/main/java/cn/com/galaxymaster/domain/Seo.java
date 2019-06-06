package cn.com.galaxymaster.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Seo Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Seo implements Serializable {

	/**
	 * SeoID
	 */
	private int seoId;

	/**
	 * SEO 标题
	 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String seoTitle;

	/**
	 * SEO 关键词
	 */
	@NotBlank
	@Length(min = 1, max = 128, message = "{message.length.error}")
	private String seoKeywords;

	/**
	 * SEO 描述
	 */
	@Length(max = 128, message = "{message.length.error}")
	private String seoDescribe;

	/**
	 * SEO ICON
	 */
	private String seoIcon;

}
