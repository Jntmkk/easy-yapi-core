package cn.hellobike.hippo.yapi.service;

import cn.hellobike.hippo.exception.HttpRequestException;
import cn.hellobike.hippo.exception.YaPiException;
import cn.hellobike.hippo.yapi.Utils;
import cn.hellobike.hippo.yapi.YaPiSdk;
import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import cn.hellobike.hippo.yapi.request.AddCategoryRequest;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateOrCreateRequest;
import cn.hellobike.hippo.yapi.response.*;
import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/27 10:23
 * @Description:
 */
@Data
@Slf4j
public class YaPiServiceImpl implements YaPiService {
    private String host;
    private String token;
    public YaPiSdk sdk;
    public YaPiServiceImpl(String host, String token) {
        this.host = host;
        this.token = token;
        this.sdk = Utils.getDefaultYaPiSDK(host, token);
    }

    @Override
    public AddInterfaceResponse addInterface(AddInterfaceRequest request) throws YaPiException {
        if (request.getCatid() == null) {
            CategoryEntity entity = getDefaultCategory(request.getProject_id());
            if (entity != null) {
                request.setCatid(String.valueOf(entity.get_id()));
            }
        }
        return sdk.addInterface(request);
    }

    @Override
    public GetInterfaceByIdResponse getInterfaceById(String id) {
        return sdk.getInterfaceById(id);
    }

    @Override
    public AddCategoryResponse addCategory(AddCategoryRequest request) {
        return sdk.addCategory(request);
    }

    @Override
    public GetProjectInfoResponse getProjectInfo(String projectId) {
        return sdk.getProjectInfo(projectId);
    }

    @Override
    public UpdateInterfaceResponse updateInterface(UpdateInterfaceRequest request) {

        GetInterfaceByIdResponse before = getInterfaceById(request.getId());
        //todo 接口变化
//		String modifyDescription = Utils.getModifyDescription(before.getData()::getRes_body, request::getRes_body);
//		request.setChange_title(modifyDescription);
//		request.setChange_param(modifyDescription);
//		log.info(modifyDescription);
        return sdk.updateInterface(request);
    }

    @Override
    public CategoryEntity getCategoryById(String projectId, String id) {
        List<CategoryEntity> categoryEntity = getAllCategoryEntity(projectId);
        for (CategoryEntity entity : categoryEntity) {
            if (entity.get_id() == Integer.valueOf(id)) {
                return entity;
            }
        }
        return null;
    }

    private List<CategoryEntity> getAllCategoryEntity(String projectId) {
        List<CategoryEntity> categoryEntities = getAllCategory(projectId).getData();
        return categoryEntities == null ? Collections.emptyList() : categoryEntities;
    }

    @Override
    public CategoryEntity containsCategoryByTitle(String projectId, String title) {
        List<CategoryEntity> categoryEntity = getAllCategoryEntity(projectId);
        for (CategoryEntity entity : categoryEntity) {
            if (entity.getName() != null && entity.getName().equals(title)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public GetAllInterfaceEntity getInterfaceByPath(String projectId, String path, String method) {
        GetAllInterfaceResponse allInterface = sdk.getAllInterface(projectId);
        if (allInterface == null || allInterface.getData() == null || allInterface.getData().getList() == null) {
            return null;
        }
        for (GetAllInterfaceEntity getAllInterfaceEntity : allInterface.getData().getList()) {
            if (getAllInterfaceEntity.getPath() != null && getAllInterfaceEntity.getPath().equals(path) && getAllInterfaceEntity.getMethod().equals(method)) {
                return getAllInterfaceEntity;
            }
        }
        return null;
    }

    @Override
    public GetInterfaceByIdResponse getInterfaceOrCreate(AddInterfaceRequest request) throws YaPiException {
        GetAllInterfaceEntity getAllInterfaceEntity = getInterfaceByPath(request.getProject_id(), request.getPath(), request.getMethod());
        long id = 0;
        if (getAllInterfaceEntity == null) {
            AddInterfaceResponse addInterfaceResponse = addInterface(request);
            if (addInterfaceResponse.getData() != null)
                id = addInterfaceResponse.getData().get_id();
        } else {
            id = getAllInterfaceEntity.get_id();
        }
        return getInterfaceById(String.valueOf(id));
    }

    /**
     * 先获得或者创建新的接口，再增量复制
     *
     * @param request
     * @return
     */
    @Override
    @SneakyThrows(YaPiException.class)
    public UpdateInterfaceResponse updateInterfaceOrCreate(UpdateInterfaceRequest request) {
        AddInterfaceRequest addInterfaceRequest = Utils.mapUpdateRequestToAdd(request);
        GetInterfaceByIdResponse interfaceOrCreate = getInterfaceOrCreate(addInterfaceRequest);

        UpdateInterfaceRequest updateInterfaceRequest = Utils.mapGetResponseToUpdateRequest(interfaceOrCreate.getData());

        Utils.updateUpdateRequest(updateInterfaceRequest, request);

        return sdk.updateInterface(updateInterfaceRequest);
    }

    @Override
    public Boolean containsService(String serviceName) {
        GetServiceResponse getServiceResponse = sdk.containsService(serviceName);
        return getServiceResponse != null && !CollectionUtil.isEmpty(getServiceResponse.getData());
    }

    @Override
    public CategoryEntity getCategoryByTitle(String projectId, String title) {
        List<CategoryEntity> categoryEntity = getAllCategoryEntity(projectId);
        for (CategoryEntity entity : categoryEntity) {
            if (entity.getName().equals(title)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public CategoryEntity getCategoryByIdOrDefault(String projectId, String id) {
        if (id == null) {
            CategoryEntity entity = getCategoryByTitle(projectId, "公共分类");
            if (entity != null) {
                id = String.valueOf(entity.get_id());
            }
        }
        return getCategoryById(projectId, id);
    }

    @Override
    public CategoryEntity getCategoryByTitleOrCreate(String projectId, String title) throws Exception {
        CategoryEntity categoryByTitle;
        if (title == null) {
            categoryByTitle = getDefaultCategory(projectId);
        } else {
            categoryByTitle = getCategoryByTitle(projectId, title);

        }
        if (categoryByTitle == null) {
            AddCategoryResponse response = sdk.addCategory(AddCategoryRequest.builder().name(title).project_id(projectId).build());
            if (response.getErrcode() != 0) {
                throw new HttpRequestException(response.getErrcode(), response.getErrmsg());
            }
            return response.getData();
        }
        return categoryByTitle;
    }

    @Override
    public AddInterfaceResponse getInterfaceByPathOrCreate(AddInterfaceRequest request) {
        return null;
    }

    @Override
    public CategoryEntity getDefaultCategory(String projectId) throws YaPiException {
        CategoryEntity categoryByTitle = getCategoryByTitle(projectId, YaPiService.DEFAULT_CATEGORY_TITLE);
        if (categoryByTitle == null) {
            throw new YaPiException("默认分类[公共分类]不存在");
        }
        return categoryByTitle;
    }

    @Override
    public GetCategoryResponse getAllCategory(String projectId) {
        return sdk.getCategoryList(projectId);
    }

    @Override
    public UpdateOrCreateResponse updateOrCreate(UpdateOrCreateRequest request) {
        return sdk.updateOrCreate(request);
    }
}