package cn.hellobike.hippo;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/6/15 20:26
 * @Description:
 */
public class VelocityUtils {
	public static String selectFirst(String... args) {
		if (args == null) {
			return null;
		}
		for (String arg : args) {
			if (arg != null) {
				return arg;
			}
		}
		return null;
	}

	public static String selectOne(String... arg) {
		if (arg == null) {
			return null;
		}
		if (arg.length == 1) {
			return arg[0];
		}
		return getRelativePath(arg[0], selectFirst(Arrays.copyOfRange(arg, 1, arg.length)));
	}

	public static String assemble(char c, String... args) {
		if (args == null) {
			return null;
		}
		if (args.length == 1) {
			return trim(args[0], c);
		}
		StringBuffer buffer = new StringBuffer();
		for (String arg : args) {
			if (arg == null) {
				continue;
			}
			buffer.append(c);
			buffer.append(trim(arg, c));
		}
		return buffer.length() == 0 ? null : buffer.toString();
	}

	public static String getRelativePath(String... args) {
		return assemble('/', args);
	}

	public static String trim(String target, char c) {
		if (target == null || target.length() == 0) {
			return null;
		}
		int left = 0, right = target.length() - 1;
		while (target.charAt(left) == c) {
			left++;
		}
		while (target.charAt(right) == c && right > left) {
			right--;
		}
		if (target.charAt(target.length() - 1) == c) {
			right--;
		}
		return target.substring(left, right + 1);
	}

	public static String trimPathSeparator(String target) {
		return trim(target, '/');
	}

	public static String json(Object obj) {
		return JSONObject.toJSONString(obj);
	}
}