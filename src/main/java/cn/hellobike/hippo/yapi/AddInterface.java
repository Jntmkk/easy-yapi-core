package cn.hellobike.hippo.yapi;

import cn.hutool.http.HttpRequest;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 17:09
 * @Description:
 */
public class AddInterface implements YapiHttpRequestBuilder {

	@Override
	public void build(HttpRequest request, YaPiApiEntityRequest t) {
		addDefaultHeader(request, t);
		request.form("catid", t.getCatId());
		request.form("method", t.getMethod());
		request.form("path", t.getPath());
		request.form("project_id", t.getProjectId());
		request.form("title", t.getTitle());
	}
}