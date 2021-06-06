package cn.hellobike.hippo.yapi.response;

import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 20:53
 * @Description:
 */
@Data
public class BaseResponse {
	private int errcode;
	private String errmsg;
}