package cn.hellobike.hippo.exception;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/29 22:13
 * @Description:
 */
public class CategoryNotExistException extends BaseException {
	public CategoryNotExistException(String message) {
		super(message);
	}

	public CategoryNotExistException(String patten, Object... obj) {
		super(String.format(patten, obj));
	}
}