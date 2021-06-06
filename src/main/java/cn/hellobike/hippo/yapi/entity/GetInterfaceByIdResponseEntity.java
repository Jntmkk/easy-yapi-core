package cn.hellobike.hippo.yapi.entity;


import lombok.Data;

import java.util.List;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 21:01
 * @Description:
 */
@Data
public class GetInterfaceByIdResponseEntity {
	/**
	 * 接口 ID
	 */
	private String _id;
	private String method;
	private int catid;
	private String title;
	private String service;
	private String path;
	private int project_id;
	private int uid;
	private long add_time;
	private long up_time;
	private int __v;
	private int index;
	private boolean api_opened;



	private List<ApiRequestBodyFormEntity> req_body_form;
	private boolean req_body_is_json_schema;
	private String req_body_other;
	private String req_body_type;

	private List<ApiRequestHeaderEntity> req_headers;
	private List<String> req_params;
	private List<String> req_query;

	private String res_body;
	private boolean res_body_is_json_schema;
	private String res_body_type;

	private QueryPath query_path;
	private String type;
	private String status;
	private int edit_uid;
	private String username;
}