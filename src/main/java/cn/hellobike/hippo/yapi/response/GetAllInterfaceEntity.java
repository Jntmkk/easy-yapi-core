/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Auto-generated: 2021-05-28 15:33:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllInterfaceEntity {
	private long _id;
	private long add_time;
	private int catid;
	private String method;
	private String path;
	private int project_id;
	private String title;
	private int uid;
	private long up_time;
	private boolean api_opened;
	private String status;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetAllInterfaceEntity that = (GetAllInterfaceEntity) o;
		return Objects.equals(path, that.path);
	}

	@Override
	public int hashCode() {
		return Objects.hash(path);
	}

	private int edit_uid;

}