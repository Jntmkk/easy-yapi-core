package cn.hellobike.hippo.classloader;

import cn.hellobike.hippo.asm.AddField;
import cn.hellobike.hippo.asm.AsmUtils;
import cn.hellobike.hippo.test.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.victools.jsonschema.generator.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddFieldTest {
	@Test
	public void testDemo() throws IOException, ClassNotFoundException {
		String path = "/Users/yuewenbo14971/IdeaProjects/untitled/module1/target/classes";
		ReloadableClassLoader classLoader = new ReloadableClassLoader(AddField.class.getClassLoader(), path);
		Class<?> cls = classLoader.loadClass("cn.User");

		classLoader.setCls(cls);

		Class<?> wrap = classLoader.getWrappedClass("cn.User");
		System.out.println(wrap);
		SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
		SchemaGeneratorConfig config = configBuilder.build();
		SchemaGenerator generator = new SchemaGenerator(config);
		ObjectNode jsonNodes = generator.generateSchema(wrap);
		System.out.println(jsonNodes);
	}

	@Test
	void testResource() throws IOException {
		InputStream resource = AsmUtils.getResource("Result.class");
		String s = IOUtils.toString(resource);
		System.out.println(s);
	}

	@Test
	void testResource2() throws IOException {
		InputStream resource = AsmUtils.getResource("Result.class");
		byte[] bytes = IOUtils.toByteArray(resource);
		ClassReader classReader = new ClassReader(bytes);
	}

}