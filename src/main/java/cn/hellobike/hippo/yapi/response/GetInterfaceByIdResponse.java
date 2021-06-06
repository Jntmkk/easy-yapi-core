package cn.hellobike.hippo.yapi.response;

import cn.hellobike.hippo.yapi.entity.GetInterfaceByIdResponseEntity;
import cn.hellobike.hippo.yapi.response.BaseResponse;
import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 20:59
 * @Description:
 */
@Data
public class GetInterfaceByIdResponse extends BaseResponse {
	private GetInterfaceByIdResponseEntity data;
}