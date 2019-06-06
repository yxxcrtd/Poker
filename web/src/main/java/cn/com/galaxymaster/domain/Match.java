package cn.com.galaxymaster.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Match Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Match implements Serializable {

	/**
	 * 赛事ID
	 */
	private int matchId;

	/**
	 * 赛事名称
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String matchName;

	/**
	 * 赛事介绍
	 */
	@Length(min = 1, max = 128, message = "{system.message.length.error}")
	private String matchSummary;

	/**
	 * 赛事开始时间
	 */
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String matchStartDate;

	/**
	 * 赛事结束时间
	 */
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String matchEndDate;

	/**
	 * 赛事地点
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String matchAddress;

	/**
	 * 赛事状态（默认0即将开始；1进行中；2已结束）
	 */
	private int matchStatus;

	/**
	 * 赛事图片
	 */
	@NotBlank
	private String matchPicture;

	/**
	 * 赛事排序
	 */
	@NotNull
	@Range(min = 1, max = 32, message = "{system.message.value.error}")
	private int matchOrderby;
	
	/**
	 * 赛事URL
	 */
	private String matchUrl;

}
