package cn.com.galaxymaster.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Log Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Log implements Serializable {

	/**
	 * LogID
	 */
	private int logId;

	/**
	 * Log Obj
	 */
	private String logObj;

	/**
	 * Log Title
	 */
	private String logTitle;

	/**
	 * LogUser
	 */
	private String logUser;
	
	/**
	 * Log Time
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logCreateTime = new Date();
	
	/**
	 * log IP
	 */
	private String logIP;

}
