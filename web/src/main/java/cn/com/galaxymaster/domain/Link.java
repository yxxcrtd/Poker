package cn.com.galaxymaster.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Link Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Link implements Serializable {

	/**
	 * LinkID
	 */
	private int linkId;

	/**
	 * LinkOrderby
	 */
	@Range(min = 1, max = 32, message = "{system.message.value.error}")
	private int linkOrderby;

	/**
	 * LinkName
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String linkName;

	/**
	 * LinkAddress
	 */
	@Length(min = 1, max = 64, message = "{system.message.length.error}")
	private String linkAddress;

	/**
	 * LinkLogo
	 */
	@NotBlank
	private String linkLogo;
	
	/**
	 * LinkStatus（默认0显示；1不显示）
	 */
	private int linkStatus;
	
}
