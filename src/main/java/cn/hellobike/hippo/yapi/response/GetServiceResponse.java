package cn.hellobike.hippo.yapi.response;

import lombok.Data;

import java.util.List;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/28 19:08
 * @Description:
 */
@Data
public class GetServiceResponse extends HelloBaseResponse {
	private List<GetServiceResponseEntity> data;
}