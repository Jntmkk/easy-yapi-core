/**
 * Copyright 2021 bejson.com
 */
/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.entity;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2021-05-26 20:57:37
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class AddInterfaceResponseEntity {
	private int __v;
	private long _id;
	private String method;
	private int catid;
	private String title;
	private String service;
	private String path;
	private int project_id;
	private String res_body_type;
	private int uid;
	private long add_time;
	private long up_time;
	private int index;
	private boolean api_opened;
	private boolean res_body_is_json_schema;
	private List<String> req_body_form;
	private boolean req_body_is_json_schema;
	private List<String> req_params;
	private List<String> req_headers;
	private List<String> req_query;
	private QueryPath query_path;
	private String type;
	private String status;
	private int edit_uid;
}