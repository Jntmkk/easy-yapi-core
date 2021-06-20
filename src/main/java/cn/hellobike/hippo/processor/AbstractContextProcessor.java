package cn.hellobike.hippo.processor;

import lombok.Data;
import org.apache.velocity.VelocityContext;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/6/14 18:32
 * @Description:
 */
@Data
public abstract class AbstractContextProcessor<T extends VelocityContext & BaseContext, V> implements ContextProcessor<T, V> {
	private ContextProcessor next;
}