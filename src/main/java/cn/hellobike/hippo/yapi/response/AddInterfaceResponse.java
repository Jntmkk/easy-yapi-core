package cn.hellobike.hippo.yapi.response;

import cn.hellobike.hippo.yapi.entity.AddInterfaceResponseEntity;
import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 20:56
 * @Description:
 */
@Data
public class AddInterfaceResponse extends BaseResponse {
	private AddInterfaceResponseEntity data;
}