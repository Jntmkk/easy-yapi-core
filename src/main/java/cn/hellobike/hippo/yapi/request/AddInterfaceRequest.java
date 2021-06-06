/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto-generated: 2021-05-26 20:49:16
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddInterfaceRequest {
	private String catid;
	private String method;
	private String path;
	private String project_id;
	private String service;
	private String title = "公共分类";
}