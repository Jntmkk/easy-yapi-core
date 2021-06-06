package cn.hellobike.hippo.yapi.response;

import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import lombok.Data;

import java.util.List;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/28 16:32
 * @Description:
 */
@Data
public class GetProjectInfoEntity {
	private boolean switch_notice;
	private boolean is_mock_open;
	private boolean strice;
	private boolean is_json5;
	private int _id;
	private String name;
	private String basepath;
	private String project_type;
	private int uid;
	private int group_id;
	private String icon;
	private String color;
	private long add_time;
	private long up_time;
	//	private List<Env> env;
	private List<String> tag;
	private List<CategoryEntity> cat;
	private String role;
}