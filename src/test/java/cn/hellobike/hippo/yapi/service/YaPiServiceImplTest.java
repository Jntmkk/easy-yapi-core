package cn.hellobike.hippo.yapi.service;

import cn.hellobike.hippo.yapi.Utils;
import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import cn.hellobike.hippo.yapi.request.AddCategoryRequest;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.response.*;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

public class YaPiServiceImplTest {
	public static final String host = "http://127.0.0.1:40001";
	public static final String token = "afadb0d289b7772fe43a9d6fa05d2206cb12166cfe264bc086a42bafd84a75c4";
	public static final YaPiService SERVICE = new YaPiServiceImpl(host, token);
	public static final String PROJECT_ID = "11";
	public static final Random RANDOM = new Random(System.currentTimeMillis());

	public static String nextInterfaceName() {
		return nextRandomString("interface");
	}

	public static String nextRandomString(String sub) {
		return "/" + sub + "-" + RANDOM.nextInt(100);
	}

	public static String nextCategoryName() {
		return nextRandomString("category");
	}

	public static String nextDesc() {
		return nextRandomString("desc");
	}

	public static String nextPath() {
		return nextRandomString("path");
	}

	public static String nextTitle() {
		return nextRandomString("title");
	}

	@Test
	public void addInterface() {
		AddInterfaceResponse addInterfaceResponse = generateInterface();
		System.out.println(addInterfaceResponse.getErrmsg());
		assertThat(addInterfaceResponse.getData()).isNotNull();

		GetInterfaceByIdResponse interfaceById = SERVICE.getInterfaceById(String.valueOf(addInterfaceResponse.getData().get_id()));

		assertThat(interfaceById.getErrcode() == 0).isTrue();
	}

	public static AddInterfaceResponse generateInterface() {
		AddCategoryResponse response = generateCat();
		assertThat(response.getData()).isNotNull();

		AddInterfaceRequest addInterfaceRequest = AddInterfaceRequest.builder().service("None")
				.catid(String.valueOf(response.getData().get_id()))
				.path(nextPath())
				.method("POST")
				.title(nextTitle())
				.project_id(PROJECT_ID)
				.build();
		AddInterfaceResponse addInterfaceResponse = SERVICE.addInterface(addInterfaceRequest);
		return addInterfaceResponse;
	}

	public static AddCategoryResponse generateCat() {
		AddCategoryRequest request = AddCategoryRequest.builder()
				.desc(nextDesc())
				.name(nextCategoryName())
				.project_id(PROJECT_ID)
				.build();
		AddCategoryResponse response = SERVICE.addCategory(request);
		return response;
	}

	@Test
	public void getInterfaceById() {

	}

	@Test
	public void addCategory() {
	}

	@Test
	public void getProjectInfo() {
	}

	@Test
	public void updateInterface() {
	}

	@Test
	public void containsCategoryById() {
		// 默认分类存在
		CategoryEntity entity = SERVICE.getCategoryById("11", "11");
		assertThat(entity).isNotNull();

		// 任意id 不存在
		CategoryEntity entity1 = SERVICE.getCategoryById("11", "121");
		assertThat(entity1).isNull();

		// 添加新接口,返回的 ID 不为空
		AddCategoryRequest request = AddCategoryRequest.builder().name(nextCategoryName()).desc(nextDesc()).project_id("11").build();
		AddCategoryResponse addCategoryResponse = SERVICE.addCategory(request);
		assertThat(addCategoryResponse.getData().get_id()).isNotNull();
		int id = addCategoryResponse.getData().get_id();

		// 判断创建的分类存在
		CategoryEntity entity2 = SERVICE.getCategoryById("11", String.valueOf(id));
		assertThat(entity2).isNotNull();

	}

	@Test
	public void containsCategoryByTitle() {
		AddCategoryResponse addCategoryResponse = generateCat();
		CategoryEntity entity = SERVICE.containsCategoryByTitle(PROJECT_ID, addCategoryResponse.getData().getName());
		assertThat(entity).isNotNull();

		CategoryEntity entity1 = SERVICE.getCategoryById(PROJECT_ID, String.valueOf(nextNotExistId()));
		assertThat(entity1).isNull();
	}

	public static long nextNotExistId() {
		return RANDOM.nextInt(100) + 100;
	}

	public static String nextNotExistPath() {
		return "/" + "path-" + nextNotExistId();
	}

	@Test
	public void containsInterfaceByPath() {
		AddInterfaceResponse addInterfaceResponse = generateInterface();
		GetAllInterfaceEntity getAllInterfaceEntity = SERVICE.containsInterfaceByPath(PROJECT_ID, addInterfaceResponse.getData().getPath());
		assertThat(getAllInterfaceEntity).isNotNull();

		GetAllInterfaceEntity getAllInterfaceEntity1 = SERVICE.containsInterfaceByPath(PROJECT_ID, nextNotExistPath());
		assertThat(getAllInterfaceEntity1).isNull();
	}


	@Test
	public void getInterfaceOrCreate() {
		AddInterfaceResponse addInterfaceResponse = generateInterface();
		assertThat(addInterfaceResponse.getData()).isNotNull();
		AddInterfaceRequest build = AddInterfaceRequest.builder()
				.project_id(PROJECT_ID)
				.title(nextTitle())
				.catid(String.valueOf(addInterfaceResponse.getData().getCatid()))
				.service("None")
				.method("GET")
				.path(addInterfaceResponse.getData().getPath())
				.build();
		GetInterfaceByIdResponse interfaceOrCreate = SERVICE.getInterfaceOrCreate(build);
		assertThat(interfaceOrCreate.getData().getPath().equals(addInterfaceResponse.getData().getPath())).isTrue();


	}

	@Test
	public void updateInterfaceOrCreate() {
		AddInterfaceResponse addInterfaceResponse = generateInterface();
		GetInterfaceByIdResponse interfaceById = SERVICE.getInterfaceById(String.valueOf(addInterfaceResponse.getData().get_id()));
		UpdateInterfaceRequest updateInterfaceRequest = Utils.mapGetResponseToUpdateRequest(interfaceById.getData());

		String res_body = "{\"data\":\"object\"}";
		updateInterfaceRequest.setRes_body(res_body);

		UpdateInterfaceResponse updateInterfaceResponse = SERVICE.updateInterfaceOrCreate(updateInterfaceRequest);

		GetInterfaceByIdResponse interfaceById1 = SERVICE.getInterfaceById(updateInterfaceRequest.getId());

		assertThat(interfaceById1).isNotNull();
		assertThat(interfaceById1.getData()).isNotNull();
		assertThat(interfaceById1.getData().getRes_body()).isNotNull();
		assertThat(interfaceById1.getData().getRes_body().equals(res_body));
	}
}