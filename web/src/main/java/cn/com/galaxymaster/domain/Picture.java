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
 * Picture Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Picture implements Serializable {

	/**
	 * PictureID
	 */
	private int pictureId;

	/**
	 * PictureGame
	 */
	private int pictureGame;

	/**
	 * PictureUri
	 */
	@NotBlank
	private String pictureUri;

	/**
	 * PictureUrl
	 */
	@NotBlank
	private String pictureUrl;

	/**
	 * PictureAlt
	 */
	@NotBlank
	@Length(min = 1, max = 30, message = "{system.message.length.error}")
	private String pictureAlt;

	/**
	 * PictureStatus（默认0显示；1不显示）
	 */
	private int PictureStatus;

	/**
	 * PictureOrderby
	 */
	@Range(min = 1, max = 32, message = "{system.message.value.error}")
	private int pictureOrderby;

}
