package cn.com.galaxymaster.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * User Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class User implements Serializable {

	/**
	 * UserID
	 */
	private int userId;

	/**
	 * Username
	 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String username;

	/**
	 * UserPassword
	 */
	@NotBlank
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String userPassword;

}
