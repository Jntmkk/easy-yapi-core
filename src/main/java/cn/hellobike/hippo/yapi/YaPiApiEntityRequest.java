package cn.hellobike.hippo.yapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 11:51
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YaPiApiEntityRequest {


	/**
	 * 是否开放
	 */
	private Boolean apiOpened;
	/**
	 * 分类 ID
	 */
	private Integer catId;
	/**
	 * 项目 ID
	 */
	private Integer id;
	/**
	 * markdown 内容
	 */
	private String markdown;
	/**
	 * 请求方法
	 */
	private String method = "POST";
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 请求参数 统一 post get ,在 yapisdk 统一转换
	 */
	private Map<String, String> params;
	/**
	 * 返回参数
	 */
	private String resBody;
	/**
	 * 返回参数格式
	 */
	private String resBodyType;
	/**
	 * 接口标题。名称
	 */
	private String title;
	/**
	 * 标签
	 */
	private String[] tag;

	private String projectId;
}