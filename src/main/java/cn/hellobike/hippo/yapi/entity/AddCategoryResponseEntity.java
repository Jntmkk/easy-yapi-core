/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.entity;

import lombok.Data;

@Data
public class AddCategoryResponseEntity {
	private int index;
	private String name;
	private int project_id;
	private String desc;
	private int uid;
	private long add_time;
	private long up_time;
	private int _id;
	private int __v;
}