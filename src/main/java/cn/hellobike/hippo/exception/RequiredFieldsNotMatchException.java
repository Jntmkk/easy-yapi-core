package cn.hellobike.hippo.exception;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/6/15 14:13
 * @Description:
 */
public class RequiredFieldsNotMatchException extends BaseException {
	public RequiredFieldsNotMatchException(String message) {
		super(message);
	}
}