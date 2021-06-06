/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiRequestBodyFormEntity {

	private String required;
	private String _id;
	private String name;
	private String type;
	private String example;
	private String desc;
}