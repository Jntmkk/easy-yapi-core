package cn.hellobike.hippo.exception;

import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 17:03
 * @Description:
 */
@Data
public class HttpRequestException extends Exception {
	private String msg;
	private int code;

	public HttpRequestException(int status, String msgFromHttpResponse) {
		super(msgFromHttpResponse);
		this.code = status;
		this.msg = msgFromHttpResponse;
	}
}