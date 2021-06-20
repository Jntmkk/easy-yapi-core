package cn.hellobike.hippo.yapi.service;

import cn.hellobike.hippo.exception.YaPiException;
import cn.hellobike.hippo.yapi.Utils;
import cn.hellobike.hippo.yapi.entity.ApiRequestHeaderEntity;
import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import cn.hellobike.hippo.yapi.request.AddCategoryRequest;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateOrCreateRequest;
import cn.hellobike.hippo.yapi.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
@Slf4j
public class YaPiServiceImplTest {
    public static final String host = "http://127.0.0.1:40001";
    public static final String token = "11ade692f92bc10e6c28eaf3d65714a564e7432960985f5dfe2f5cad5e3e0435f";
    public static final String PROJECT_ID = "11";
    public static final YaPiService SERVICE = new YaPiServiceImpl(host, token);
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
    public void addInterface() throws YaPiException {
        AddInterfaceResponse addInterfaceResponse = generateInterface();
        System.out.println(addInterfaceResponse.getErrmsg());
        assertThat(addInterfaceResponse.getData()).isNotNull();

        GetInterfaceByIdResponse interfaceById = SERVICE.getInterfaceById(String.valueOf(addInterfaceResponse.getData().get_id()));

        assertThat(interfaceById.getErrcode() == 0).isTrue();
    }

    public static AddInterfaceResponse generateInterface() throws YaPiException {
        AddCategoryResponse response = generateCat();
        log.info(response.getErrmsg());
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
    public void containsInterfaceByPath() throws YaPiException {
        AddInterfaceResponse addInterfaceResponse = generateInterface();
        GetAllInterfaceEntity getAllInterfaceEntity = SERVICE.getInterfaceByPath(PROJECT_ID, addInterfaceResponse.getData().getPath(), "POST");
        assertThat(getAllInterfaceEntity).isNotNull();

        GetAllInterfaceEntity getAllInterfaceEntity1 = SERVICE.getInterfaceByPath(PROJECT_ID, nextNotExistPath(), "POST");
        assertThat(getAllInterfaceEntity1).isNull();
    }


    @Test
    public void getInterfaceOrCreate() throws YaPiException {
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
    public void updateInterfaceOrCreate() throws YaPiException {
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

    @Test
    public void updateOrCreate() throws YaPiException {
        UpdateOrCreateRequest request = UpdateOrCreateRequest.builder()
                .req_headers(Arrays.asList(ApiRequestHeaderEntity.builder().name("Content-Type").value("application/json").build()))
                .desc("desc")
                .message("message")
                .method("POST")
                .catid(15)
                .req_body_form(Collections.emptyList())
                .service("App")
                .switch_notice(true)
                .id("11")
                .req_body_type("json")
                .title("title")
                .req_body_other("{}")
                .res_body("{}")
                .res_body_type("json")
                .token("0ad3d038a1efed2f14c99a25dfa86c77c7e8b6bf2f5044d325ac77ed98bb92ec")
                .path("/path")
                .status("done")
                .build();
        UpdateOrCreateResponse updateOrCreateResponse = SERVICE.updateOrCreate(request);
        System.out.println(updateOrCreateResponse.getErrmsg());
    }
}