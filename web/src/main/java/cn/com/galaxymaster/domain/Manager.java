package cn.com.galaxymaster.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Manager Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Manager implements Serializable {

	/**
	 * 管理员ID
	 */
	private int managerId;

	/**
	 * 管理员名称
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String managerName;

	/**
	 * 管理员密码
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String managerPassword;

	/**
	 * 管理员角色（系统管理员默认是：1；其他内容管理员都是：0；）
	 */
	private int managerRole;
	
	/**
	 * 1，新闻权限
	 */
	private boolean managerNews;
	
	/**
	 * 2，视频权限
	 */
	private boolean managerVideo;
	
	/**
	 * 3，广告权限
	 */
	private boolean managerAd;
	
	/**
	 * 4，游戏库权限
	 */
	private boolean managerGame;
	
	/**
	 * 5，活动权限
	 */
	private boolean managerActive;
	
	/**
	 * 6，赛事权限
	 */
	private boolean managerMatch;
	
	/**
	 * 7，网站设置权限
	 */
	private boolean managerWebsite;
	
	/**
	 * 8，棋牌管理权限
	 */
	private boolean managerPoker;
	
}
