package cn.hellobike.hippo.asm;

import cn.hellobike.hippo.classloader.ReloadableClassLoader;
import cn.hellobike.hippo.yapi.Utils;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/25 21:09
 * @Description:
 */
public class AsmUtils {
	public static final String RESULT_QUALIFY_NAME = "cn.hellobike.hippo.asm.Result";

	public static InputStream getResource(String resource) {
		return AsmUtils.class.getClassLoader().getResourceAsStream(resource);
	}

	public static InputStream getTemplateResourceAsStream() {
		return getResource("Result.class");
	}

	public static byte[] addField(String fieldName, Class cls) throws IOException {
		ClassReader classReader = new ClassReader(AsmUtils.getTemplateResourceAsStream());
		ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
		ClassVisitor addField = new AddField(classWriter,
				fieldName,
				Opcodes.ACC_PRIVATE,
				Type.getDescriptor(cls),
				"demo"
		);
		classReader.accept(addField, ClassReader.EXPAND_FRAMES);
		byte[] bytes = classWriter.toByteArray();
		return bytes;
	}

	public static Class getWrappedClass(Class cls) throws IOException {
		ReloadableClassLoader classLoader = new ReloadableClassLoader(AsmUtils.class.getClassLoader(), null);
		return classLoader.defineClass("cn.hellobike.hippo.asm.Result", addField("data", cls));
	}


	public static String getWrappedClassJsonSchemaAsString(Class cls) throws IOException {
		return Utils.getClassJasonSchema(getWrappedClass(cls));
	}
}