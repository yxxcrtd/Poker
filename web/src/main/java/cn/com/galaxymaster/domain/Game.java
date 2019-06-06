package cn.com.galaxymaster.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Game Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Game implements Serializable {

	/**
	 * ID
	 */
	private int gameId;

	/**
	 * 游戏投放位置（1：棋类；2：牌类；3，麻将；9其他）
	 */
	private int gameCategoryId;

	/**
	 * 游戏产品类型（0：8大单品；1：其他游戏）
	 */
	private int gameType;

	/**
	 * 游戏标题
	 */
	@NotBlank
	@Length(min = 1, max = 16, message = "{system.message.length.error}")
	private String gameTitle;

	/**
	 * 游戏描述
	 */
	@NotBlank
	@Length(min = 1, max = 1000, message = "{system.message.length.error}")
	private String gameHelp;

	/**
	 * 棋牌规则
	 */
	@NotBlank
	@Length(min = 1, max = 4000, message = "{system.message.length.error}")
	private String gameRule;

	/**
	 * 游戏官网地址
	 */
	@NotBlank
	@Length(max = 128, message = "{system.message.length.error}")
	private String gameOfficialUrl;
	/**
	 * 游戏下载地址
	 */
	@NotBlank
	@Length(max = 128, message = "{system.message.length.error}")
	private String gameLoadUrl;

	/**
	 * 游戏图片
	 */
	@NotBlank
	@Length(max = 128, message = "{system.message.length.error}")
	private String gamePicture;

	/**
	 * 游戏状态（0：使用中（默认）；1：停止；）
	 */
	private int gameStatus;

	/**
	 * 棋牌规则状态（0：使用中（默认）；1：停止；）
	 */
	private int gameRuleStatus;

	/**
	 * 游戏排序
	 */
	@Range(min = 0, message = "{system.message.value.error}")
	private int gameOrderby;

	/**
	 * 游戏更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gameUpdateTime = new Date();

}
