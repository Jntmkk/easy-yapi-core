package cn.hellobike.hippo.processor;


import cn.hellobike.hippo.annotation.YaPiApiEntity;
import org.apache.velocity.VelocityContext;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/6/14 18:17
 * @Description:
 */
public interface ContextProcessor<T extends VelocityContext & BaseContext, V> extends Order {
	void process(T context, V entity) throws Exception;

	ContextProcessor<T, V> getNext();

	default void doNext(T context, V entity) throws Exception {
		ContextProcessor<T, V> next = getNext();
		if (next != null) {
			next.process(context, entity);
		}
	}
}
