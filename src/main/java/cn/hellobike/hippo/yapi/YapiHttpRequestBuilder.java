package cn.hellobike.hippo.yapi;

import cn.hutool.http.HttpRequest;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 16:55
 * @Description:
 */
@FunctionalInterface
public interface YapiHttpRequestBuilder {
	void build(HttpRequest request, YaPiApiEntityRequest t);

	default void addDefaultHeader(HttpRequest request, YaPiApiEntityRequest t) {
		request.header("Accept", "application/json, text/plain, */*");
		request.header("Accept-Encoding", "gzip, deflate, br");
		request.header("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
		request.header("Connection", "keep-alive");
	}
//	default JSONObject execute(HttpRequest request, T t) throws HttpRquestError {
//		HttpResponse response = request.execute();
//		if (!response.isOk()) {
//			throw new HttpRquestError(response.getStatus(), Utils.getMsgFromHttpResponse(response));
//		}
//		return JSONObject.parseObject(response.body());
//	}
}
