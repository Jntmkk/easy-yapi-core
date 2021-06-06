package cn.hellobike.hippo.yapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {
	private int index;
	private int _id;
	private String name;
	private int project_id;
	private String desc;
	private int uid;
	private long add_time;
	private long up_time;
	private int __v;
}