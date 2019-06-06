package cn.com.galaxymaster.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ${obj} Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class ${obj} implements Serializable {

	/**
	 * ${obj}ID
	 */
	private int ${obj?uncap_first}Id;

	/**
	 * ${obj}
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String ${obj?uncap_first}_1;

	/**
	 * ${obj}
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String ${obj?uncap_first}_2;

	/**
	 * ${obj}
	 */
	@Length(min = 1, max = 32, message = "{message.length.error}")
	private String ${obj?uncap_first}_3;

}
