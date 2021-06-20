package cn.hellobike.hippo.processor;

import cn.hellobike.hippo.yapi.service.YaPiService;
import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/6/14 20:12
 * @Description:
 */
@Data
public abstract class AbstractBaseContext implements BaseContext {
	private YaPiService yaPiService;

	public AbstractBaseContext(YaPiService yaPiService) {
		this.yaPiService = yaPiService;
	}
}