package cn.hellobike.hippo.processor;

import cn.hellobike.hippo.exception.RequiredFieldsNotMatchException;
import org.apache.velocity.VelocityContext;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/6/15 14:07
 * @Description:
 */
public class ValidatorProcessor<T extends VelocityContext & BaseContext, V> extends AbstractContextProcessor<T, V> {
	public static final String[] FIELDS = new String[]{"method", "annotation", "cls"};

	@Override
	public void process(T context, V entity) throws Exception {
		for (String field : FIELDS) {
			if (context.get(field) == null) {
				throw new RequiredFieldsNotMatchException(field);
			}
		}
	}

	@Override
	public int getOrder() {
		return Order.LAST;
	}
}