/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.request;

import cn.hellobike.hippo.yapi.entity.ApiRequestBodyFormEntity;
import cn.hellobike.hippo.yapi.entity.ApiRequestHeaderEntity;
import cn.hellobike.hippo.yapi.entity.ApiRequestParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Auto-generated: 2021-06-08 20:27:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrCreateRequest {
	private Integer projectId;
	private String id;
	private String title;
	private String path;
	private String method;
	private List<ApiRequestHeaderEntity> req_headers;
	private String req_body_type;
	private List<ApiRequestParam> req_params;
	private List<ApiRequestBodyFormEntity> req_body_form;
	private String req_body_other;
	private String res_body_type;
	private String res_body;

	private String token;
	private List<String> req_query;
	private Integer catid;
	@JsonIgnore
	private String catText;
	private String status;
	private boolean switch_notice;
	private String message;
	private String desc;
	private String service;
}