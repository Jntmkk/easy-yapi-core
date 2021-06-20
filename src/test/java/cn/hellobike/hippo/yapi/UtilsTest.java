package cn.hellobike.hippo.yapi;

import cn.hellobike.hippo.VelocityUtils;
import cn.hellobike.hippo.annotation.YaPiApiEntity;
import cn.hellobike.hippo.asm.AsmUtils;
import cn.hellobike.hippo.test.User;
import cn.hellobike.hippo.yapi.entity.ApiRequestHeaderEntity;
import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import cn.hellobike.hippo.yapi.request.AddCategoryRequest;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.response.AddCategoryResponse;
import cn.hellobike.hippo.yapi.response.AddInterfaceResponse;
import cn.hellobike.hippo.yapi.response.GetInterfaceByIdResponse;
import cn.hellobike.hippo.yapi.response.UpdateInterfaceResponse;
import cn.hellobike.hippo.yapi.service.YaPiService;
import cn.hellobike.hippo.yapi.service.YaPiServiceImpl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class UtilsTest {
	public static final String host = "http://127.0.0.1:40001";
	public static final String token = "1b921ff7c537bd7bf70cbdfae6be2f80d01a50f43fdc54f59bee92ff0fe8edf6";
	public static final YaPiSdk sdk = Utils.getDefaultYaPiSDK(host, token);
	YaPiService service = new YaPiServiceImpl(host, token);

	@Test
	public void getInterface() {
		GetInterfaceByIdResponse interfaceById = service.getInterfaceById("111");
		log.info(interfaceById.getErrmsg());
	}

	@Test
	public void addInterface() {
		AddInterfaceRequest request = AddInterfaceRequest.builder()
				.method("POST")
				.path("/request")
				.title("request")
				.catid("19")
				.project_id("11")
//				.service("")
				.build();

		AddInterfaceResponse addInterfaceResponse = sdk.addInterface(request);
		System.out.println(addInterfaceResponse.getErrmsg());
	}

	@Test
	public void addCat() {
		AddCategoryRequest request = AddCategoryRequest.builder()
				.desc("desc")
				.name("测试类别")
				.project_id("11")
				.build();
		AddCategoryResponse response = sdk.addCategory(request);
		System.out.println(response.getErrmsg());
	}

	@Test
	public void updateInterface() throws IOException {
		UpdateInterfaceRequest request = new UpdateInterfaceRequest();
		GetInterfaceByIdResponse pre = service.getInterfaceById("11");
		BeanUtil.copyProperties(pre.getData(), request, true);
		request.setDesc("test desc qwwwwwwwwwwwwwwwww");
		request.setId(pre.getData().get_id());
		request.setDesc(null);
		request.setMarkdown(null);
		request.setRes_body(AsmUtils.getWrappedClassJsonSchemaAsString(User.class));
		UpdateInterfaceResponse response = service.updateInterface(request);

		System.out.println(response.getErrmsg());
	}

	@Test
	public void testBean() {
		CategoryEntity origin = new CategoryEntity();
		CategoryEntity cur = CategoryEntity.builder().desc("dsc").build();
		BeanUtil.copyProperties(origin, cur, CopyOptions.create().ignoreNullValue().ignoreCase());
		System.out.println(cur);


	}

	@Test
	public void testVec() throws IOException {
		Velocity.init();
		VelocityContext context = new VelocityContext();
		YaPiApiEntity entity = YaPiApiEntity.builder()
				.desc("desc")
				.method("POST")
//				.title("title")
//				.path("/path")
				.build();
		context.put("annotation", entity);
//		context.put("comment", "comment");
		context.put("VelocityUtils", VelocityUtils.class);
		List<ApiRequestHeaderEntity> requestHeaderEntities = new LinkedList<>();
		requestHeaderEntities.add(ApiRequestHeaderEntity.builder().name("Content-Type").value("application/json").build());
		context.put("req_headers", requestHeaderEntities);
		StringWriter writer = new StringWriter();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("request.json");
		String s = IOUtils.toString(inputStream, "utf8");
		JSONObject jsonObject1 = JSONObject.parseObject(s);
		System.out.println(jsonObject1);
		Velocity.evaluate(context, writer, "my", s);
		JSONObject jsonObject = JSONObject.parseObject(writer.toString());
		System.out.println(jsonObject.toJSONString());
	}
}