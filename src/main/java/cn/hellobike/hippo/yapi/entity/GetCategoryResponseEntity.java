/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.entity;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2021-05-26 21:12:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class GetCategoryResponseEntity {
	private int _id;
	private String name;
	private int project_id;
	private String desc;
	private int uid;
	private long add_time;
	private long up_time;
	private int __v;
	private int index;
	private List<GetCategoryResponseInterfaceEntity> list;
}