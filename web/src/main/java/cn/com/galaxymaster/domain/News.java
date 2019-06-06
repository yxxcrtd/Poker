package cn.com.galaxymaster.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * News Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class News implements Serializable {

	/**
	 * 新闻ID
	 */
	private int newsId;

	/**
	 * 新闻状态（默认0显示；1不显示）
	 */
	private int newsStatus;

	/**
	 * 新闻分类（默认0综合新闻；1德州扑克；2斗地主；3麻将；4棋类；...9其他）
	 */
	private int newsCategory;

	/**
	 * 新闻类型（默认0新闻；1赛事；2公告）
	 */
	private int newsType;

	/**
	 * 新闻标题
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String newsTitle;

	/**
	 * 新闻摘要
	 */
	@Length(min = 1, max = 128, message = "{system.message.length.error}")
	private String newsSummary;

	/**
	 * 新闻内容
	 */
	@NotBlank
	private String newsContent;

	/**
	 * 新闻图片
	 */
	@NotBlank
	private String newsPicture;
	
	/**
	 * 新闻点击量
	 */
	private int newsHit;

	/**
	 * 新闻关键字
	 */
	@NotNull
	private String newsKeyword;

	/**
	 * 新闻排序
	 */
	private int newsOrderby;

	/**
	 * 新闻时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date newsCreateTime = new Date();
	
}
