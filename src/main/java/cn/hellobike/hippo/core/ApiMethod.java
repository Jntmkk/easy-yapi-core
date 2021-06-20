package cn.hellobike.hippo.core;

import cn.hellobike.hippo.annotation.YaPiApiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/6/14 21:48
 * @Description:
 */
public interface ApiMethod<S> {
	String getMethodName();

	String returnTypeQualifyName();

	String getParamsCount();

	String getParam(int index);

	S getAnnotation();
}