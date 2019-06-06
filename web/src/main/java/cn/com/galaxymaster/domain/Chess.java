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
 * Chess Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Chess implements Serializable {

	private int chessId;
	/**
	 * 棋谱标题
	 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String chessTitle;
	/**
	 * 棋谱关键字
	 */
	@NotBlank
	@Length(min = 1, max = 128, message = "{system.message.length.error}")
	private String chessSummary;
	/**
	 * 棋谱过程（数组）
	 */
	@Length(min = 20, max = 4000, message = "{system.message.length.error}")
	private String chessProcess;

	/**
	 * 红方
	 */
	@NotBlank
	@Length(min = 1, max = 8, message = "{system.message.length.error}")
	private String chessRed;
	/**
	 * 红方国际
	 */
//	private String chessRedCountry = "中国";
	/**
	 * 红方称号
	 */
	@NotBlank
	@Length(min = 1, max = 8, message = "{system.message.length.error}")
	private String chessRedLevel;
	/**
	 * 红方图片
	 */
	private String chessRedPic;
	/**
	 * b方
	 */
	@NotBlank
	@Length(min = 1, max = 8, message = "{system.message.length.error}")
	private String chessBlack;
	/**
	 * b方国际
	 */
//	private String chessBlackCountry = "中国";
	/**
	 * b方称号
	 */
	@NotBlank
	@Length(min = 1, max = 8, message = "{system.message.length.error}")
	private String chessBlackLevel;
	/**
	 * 结果:0r,1b
	 */
	private int chessResult;
	/**
	 * b方图片
	 */
	private String chessBlackPic;
	/**
	 * 点击
	 */
	private int chessHit;
	/**
	 * 排序
	 */
	private int chessOrderby;
	/**
	 * 状态
	 */
	private int chessStatus;
	/**
	 * 添加人
	 */
	@NotBlank
	@Length(min = 1, max = 16, message = "{system.message.length.error}")
	private String chessCreateUser;
	/**
	 * 时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date chessCreateTime = new Date();

}
