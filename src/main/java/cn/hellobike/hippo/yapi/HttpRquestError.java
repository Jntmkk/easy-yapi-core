package cn.hellobike.hippo.yapi;

import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 17:03
 * @Description:
 */
@Data
public class HttpRquestError extends Exception {
	private String msg;
	private int code;

	public HttpRquestError(int status, String msgFromHttpResponse) {
		this.code = status;
		this.msg = msgFromHttpResponse;
	}
}