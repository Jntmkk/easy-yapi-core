package cn.hellobike.hippo.yapi.entity;

import lombok.Data;

import java.util.List;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 20:51
 * @Description:
 */
@Data
public class QueryPath {
	private List<String> params;
	private String path;
}