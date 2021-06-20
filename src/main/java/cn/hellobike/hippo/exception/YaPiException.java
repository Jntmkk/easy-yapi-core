package cn.hellobike.hippo.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/27 10:06
 * @Description:
 */
public class YaPiException extends Exception {

	public YaPiException(String message) {
		super(message);
	}
}