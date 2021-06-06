package cn.hellobike.hippo.yapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 21:22
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCategoryRequest {
	private String desc;
	private String name;
	private String project_id;
}