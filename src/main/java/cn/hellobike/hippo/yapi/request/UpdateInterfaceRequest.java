/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.request;

import cn.hellobike.hippo.yapi.entity.ApiRequestBodyFormEntity;
import cn.hellobike.hippo.yapi.entity.ApiRequestHeaderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Auto-generated: 2021-05-27 10:47:52
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInterfaceRequest {
	private List<ApiRequestHeaderEntity> req_headers;
	private List<String> req_query;
	private List<ApiRequestBodyFormEntity> req_body_form;
	private String title;
	private String catid;
	private String service;
	private String path;
	private String status;
	private String change_title;
	private String change_ver;
	private String change_param;
	private String change_table;
	private String req_body_type;
	private Boolean req_body_is_json_schema = true;
	private Boolean res_body_is_json_schema = true;
	private String res_body_type;
	private String res_body;
	private boolean switch_notice;
	private boolean api_opened;
	private String desc;
	private String markdown;
	private String req_body_other;
	private String method;
	private List<String> req_params;
	private String id;
}