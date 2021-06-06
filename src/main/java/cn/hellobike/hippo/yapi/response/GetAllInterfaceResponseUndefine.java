/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.response;

import cn.hellobike.hippo.yapi.entity.ApiRequestBodyFormEntity;
import cn.hellobike.hippo.yapi.entity.ApiRequestHeaderEntity;
import cn.hellobike.hippo.yapi.entity.QueryPath;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2021-05-28 14:56:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class GetAllInterfaceResponseUndefine extends BaseResponse{

	private QueryPath query_path;
	private int edit_uid;
	private String status;
	private String type;
	private boolean req_body_is_json_schema;
	private boolean res_body_is_json_schema;
	private boolean api_opened;
	private int index;
	private List<String> tag;
	private int _id;
	private int catid;
	private String method;
	private String path;
	private int project_id;
	private String title;
	private List<String> req_params;
	private String res_body_type;
	private int uid;
	private long add_time;
	private long up_time;
	private List<String> req_query;
	private List<ApiRequestHeaderEntity> req_headers;
	private List<ApiRequestBodyFormEntity> req_body_form;
	private int __v;
	private String desc;
	private String markdown;
	private String res_body;
	private String req_body_type;
	private String username;
}