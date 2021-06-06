package cn.hellobike.hippo.yapi.response;

import lombok.Data;

import java.util.List;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/28 15:35
 * @Description:
 */
@Data
public class GetAllInterfaceResponseEntity {
	private Integer count;
	private Integer total;
	private List<GetAllInterfaceEntity> list;
}