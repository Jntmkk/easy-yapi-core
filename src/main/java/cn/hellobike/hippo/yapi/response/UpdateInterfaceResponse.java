package cn.hellobike.hippo.yapi.response;

import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/27 10:48
 * @Description:
 */
@Data
public class UpdateInterfaceResponse extends BaseResponse {
	private Integer n;
	private Integer nModified;
	private Integer ok;
}