package cn.hellobike.hippo.yapi.entity;

import lombok.Data;


@Data
public class GetCategoryResponseInterfaceEntity {
	private long _id;
	private long add_time;
	private int catid;
	private String method;
	private String path;
	private int project_id;
	private String title;
	private int uid;
	private long up_time;
	private int index;
	private String status;
	private int edit_uid;
}