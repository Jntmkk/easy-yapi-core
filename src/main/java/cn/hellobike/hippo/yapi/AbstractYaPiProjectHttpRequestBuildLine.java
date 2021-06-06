package cn.hellobike.hippo.yapi;

import cn.hutool.http.HttpRequest;
import lombok.Data;

import java.net.HttpCookie;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 16:49
 * @Description:
 */
@Data
public abstract class AbstractYaPiProjectHttpRequestBuildLine<T> extends AbstractYaPiHttpRequestBuildLine<T> {
	private String token;
	private Integer projectId;

	public AbstractYaPiProjectHttpRequestBuildLine(String token, Integer projectId) {
		this.token = token;
		this.projectId = projectId;
	}

	@Override
	public void preExecute(HttpRequest request, T t) {
		super.preExecute(request, t);
		request.cookie(new HttpCookie("_yapi_token", token));
		request.cookie(new HttpCookie("_yapi_uid", projectId.toString()));
	}
}