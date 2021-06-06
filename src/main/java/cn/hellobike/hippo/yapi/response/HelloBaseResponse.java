package cn.hellobike.hippo.yapi.response;

import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/28 19:12
 * @Description:
 */
@Data
public class HelloBaseResponse {
	private String msg;
	private Boolean status;
}