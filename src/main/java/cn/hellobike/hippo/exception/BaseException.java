package cn.hellobike.hippo.exception;

import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/29 22:12
 * @Description:
 */
@Data
public class BaseException extends Exception {
	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}
}