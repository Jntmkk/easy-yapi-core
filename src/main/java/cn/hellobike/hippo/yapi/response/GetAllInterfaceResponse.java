package cn.hellobike.hippo.yapi.response;

import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/28 15:32
 * @Description:
 */
@Data
public class GetAllInterfaceResponse extends BaseResponse {
	private GetAllInterfaceResponseEntity data;
}