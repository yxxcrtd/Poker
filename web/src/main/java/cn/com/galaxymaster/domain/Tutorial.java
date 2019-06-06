package cn.com.galaxymaster.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * tutorial Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Tutorial implements Serializable {

	/**
	 * 棋牌教程ID
	 */
	private int tutorialId;

	/**
	 * 棋牌教程分类（默认0综合棋牌教程；1德州扑克；2斗地主；3麻将；4棋类；...9其他）
	 */
	private int tutorialCategory;

	/**
	 * 棋牌教程标题
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String tutorialTitle;

	/**
	 * 棋牌教程摘要
	 */
	@Length(min = 1, max = 128, message = "{system.message.length.error}")
	private String tutorialSummary;
	/**
	 * 棋牌教程关键字
	 */
	@NotBlank
	@Length(min = 1, max = 16, message = "{system.message.length.error}")
	private String tutorialKeyword;
	/**
	 * 棋牌教程内容
	 */
	@NotBlank
	private String tutorialContent;

	/**
	 * 棋牌教程图片
	 */
	@Length(min = 0, max = 64, message = "{system.message.length.error}")
	private String tutorialPicture;

	/**
	 * 棋牌教程点击量
	 */
	private int tutorialHit;

	/**
	 * 棋牌教程状态（默认0显示；1不显示）
	 */
	private int tutorialStatus;
	/**
	 * 棋牌教程排序
	 */
	private int tutorialOrderby;
	/**
	 * 创建人
	 */
	@Length(min = 1, max = 16, message = "{system.message.length.error}")
	private String tutorialCreateUser;
	/**
	 * 棋牌教程时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date tutorialCreateTime = new Date();

}
