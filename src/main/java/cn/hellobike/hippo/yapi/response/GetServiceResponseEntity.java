/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.response;

import lombok.Data;

import java.util.Date;

/**
 * Auto-generated: 2021-05-28 19:9:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class GetServiceResponseEntity {
	private int id;
	private String user;
	private String user_email;
	private String system;
	private String team;
	private String name;
	private String lang;
	private Date create_date;
	private Date update_date;
}