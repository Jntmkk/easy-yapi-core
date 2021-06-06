package cn.hellobike.hippo.yapi.parse;

import cn.hellobike.hippo.yapi.YaPiException;
import cn.hellobike.hippo.yapi.response.BaseResponse;
import feign.Response;
import feign.jackson.JacksonDecoder;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/27 10:14
 * @Description:
 */
public class DefaultJacksonDecoder extends JacksonDecoder {
	@Override
	public Object decode(Response response, Type type) throws IOException {
		Object decode = super.decode(response, type);
		if (decode instanceof BaseResponse) {
			BaseResponse response1 = (BaseResponse) decode;
//			if (response1.getErrcode() != 0) {
//				throw new YaPiException(response1.getErrcode(), response1.getErrmsg());
//			}
		}
		return decode;
	}
}