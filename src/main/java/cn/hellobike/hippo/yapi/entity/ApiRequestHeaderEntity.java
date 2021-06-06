package cn.hellobike.hippo.yapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/27 16:41
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRequestHeaderEntity {
	private String required;
	private String _id;
	private String name;
	private String value;
}