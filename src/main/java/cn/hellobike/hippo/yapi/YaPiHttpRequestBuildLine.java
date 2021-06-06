package cn.hellobike.hippo.yapi;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 16:37
 * @Description:
 */
public interface YaPiHttpRequestBuildLine<T> {
	default void build(HttpRequest request, T t) {
	}

	default void preExecute(HttpRequest request, T t) {

	}

	default HttpResponse execute(HttpRequest request, T t) {
		return request.execute();
	}

	default void afterExecute(HttpRequest request, HttpResponse response, T t) {

	}
}
