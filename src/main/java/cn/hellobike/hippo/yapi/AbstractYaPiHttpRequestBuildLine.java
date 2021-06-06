package cn.hellobike.hippo.yapi;

import cn.hutool.http.HttpRequest;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 16:42
 * @Description:
 */
public abstract class AbstractYaPiHttpRequestBuildLine<T> implements YaPiHttpRequestBuildLine<T> {
	@Override
	public void preExecute(HttpRequest request, T t) {
		request.header("Accept", "application/json, text/plain, */*");
		request.header("Accept-Encoding", "gzip, deflate, br");
		request.header("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
		request.header("Connection", "keep-alive");
	}
}