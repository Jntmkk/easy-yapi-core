package cn.hellobike.hippo.yapi;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 16:51
 * @Description:
 */
public class DefaultYaPiProjectHttpRequestBuildLine<T> extends AbstractYaPiProjectHttpRequestBuildLine<T> {

	public DefaultYaPiProjectHttpRequestBuildLine(String token, Integer projectId) {
		super(token, projectId);
	}

}