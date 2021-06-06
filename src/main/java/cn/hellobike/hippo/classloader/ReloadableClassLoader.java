package cn.hellobike.hippo.classloader;

import cn.hellobike.hippo.asm.AddField;
import cn.hellobike.hippo.asm.AsmUtils;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/24 00:54
 * @Description:
 */
@Data
public class ReloadableClassLoader extends ClassLoader {
	private String classPath;
	private Class cls;

	public ReloadableClassLoader(ClassLoader parent, String classPath) {
		super(parent);
		this.classPath = classPath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			String absolutePath = getAbsolutePath(name);
			byte[] bytes = getByteFromFile(new File(absolutePath));
			return defineClass(name, bytes, 0, bytes.length);
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

	public Class defineClass(String name, byte[] bytes) {
		return defineClass(name, bytes, 0, bytes.length);
	}

	public Class getWrappedClass(String name) throws ClassNotFoundException, IOException {
		Class<?> target = Class.forName(name, true, this);
		byte[] bytes = AsmUtils.addField("data", target);
		return defineClass(AsmUtils.RESULT_QUALIFY_NAME, bytes, 0, bytes.length);
	}

	public byte[] getBytesFromQualifyName(String qualifyName) throws IOException {
		return getByteFromFile(new File(getAbsolutePath(qualifyName)));
	}

	public byte[] getByteFromFile(File file) throws IOException {
		return FileUtils.readFileToByteArray(file);
	}

	public String getAbsolutePath(String qualifyName) {
		return Paths.get(classPath, qualifyName.replace(".", FileSystems.getDefault().getSeparator()) + ".class").toString();
	}
}