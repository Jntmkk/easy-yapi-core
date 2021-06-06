package cn.hellobike.hippo.yapi.service;

import cn.hellobike.hippo.exception.CategoryNotExistException;
import cn.hellobike.hippo.yapi.Utils;
import cn.hellobike.hippo.yapi.YaPiSdk;
import cn.hellobike.hippo.yapi.entity.ApiRequestHeaderEntity;
import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import cn.hellobike.hippo.yapi.request.AddCategoryRequest;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.response.*;
import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
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
	public AddInterfaceResponse addInterface(AddInterfaceRequest request) {
		if (request.getCatid() == null) {
			CategoryEntity entity = getCategoryByTitle(request.getProject_id(), "公共分类");
			request.setCatid(String.valueOf(entity.get_id()));
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
		if (request.getReq_body_is_json_schema() == null) {
			request.setReq_body_is_json_schema(Boolean.TRUE);
		}
		if (request.getRes_body_is_json_schema() == null) {
			request.setRes_body_is_json_schema(Boolean.TRUE);
		}
		if (CollectionUtil.isEmpty(request.getReq_headers())) {
			request.setReq_headers(Arrays.asList(ApiRequestHeaderEntity
					.builder()
					.name("Content-Type")
					.value("application/json")
					.required("true")
					.build()));
		}
		//todo 接口变化
//		String modifyDescription = Utils.getModifyDescription(before.getData()::getRes_body, request::getRes_body);
//		request.setChange_title(modifyDescription);
//		request.setChange_param(modifyDescription);
//		log.info(modifyDescription);
		return sdk.updateInterface(request);
	}

	@Override
	public CategoryEntity getCategoryById(String projectId, String id) {
		List<CategoryEntity> categoryEntity = getCategoryEntity(projectId);
		for (CategoryEntity entity : categoryEntity) {
			if (entity.get_id() == Integer.valueOf(id)) {
				return entity;
			}
		}
		return null;
	}

	private List<CategoryEntity> getCategoryEntity(String projectId) {
		GetProjectInfoResponse projectInfo = getProjectInfo(projectId);
		if (projectInfo == null || projectInfo.getData() == null || projectInfo.getData().getCat() == null) {
			return Collections.emptyList();
		}
		return projectInfo.getData().getCat();
	}

	@Override
	public CategoryEntity containsCategoryByTitle(String projectId, String title) {
		List<CategoryEntity> categoryEntity = getCategoryEntity(projectId);
		for (CategoryEntity entity : categoryEntity) {
			if (entity.getName() != null && entity.getName().equals(title)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public GetAllInterfaceEntity containsInterfaceByPath(String projectId, String path) {
		GetAllInterfaceResponse allInterface = sdk.getAllInterface(projectId);
		if (allInterface == null || allInterface.getData() == null || allInterface.getData().getList() == null) {
			return null;
		}
		for (GetAllInterfaceEntity getAllInterfaceEntity : allInterface.getData().getList()) {
			if (getAllInterfaceEntity.getPath() != null && getAllInterfaceEntity.getPath().equals(path)) {
				return getAllInterfaceEntity;
			}
		}
		return null;
	}

	@Override
	public GetInterfaceByIdResponse getInterfaceOrCreate(AddInterfaceRequest request) {
		GetAllInterfaceEntity getAllInterfaceEntity = containsInterfaceByPath(request.getProject_id(), request.getPath());
		long id = 0;
		if (getAllInterfaceEntity == null) {
			AddInterfaceResponse addInterfaceResponse = addInterface(request);
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
		List<CategoryEntity> categoryEntity = getCategoryEntity(projectId);
		for (CategoryEntity entity : categoryEntity) {
			if (entity.getName().equals(title)) {
				return entity;
			}
		}
		System.out.println("projectId = " + projectId + ", title = " + title);
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
	public CategoryEntity getCategoryByTitleOrCreate(String projectId, String id) {
		return null;
	}

	@Override
	public AddInterfaceResponse getInterfaceByPathOrCreate(AddInterfaceRequest request) {
		return null;
	}

	@Override
	public CategoryEntity getDefaultCategory(String projectId) throws CategoryNotExistException {
		CategoryEntity categoryByTitle = getCategoryByTitle(projectId, YaPiService.DEFAULT_CATEGORY_TITLE);
		if (categoryByTitle == null) {
			throw new CategoryNotExistException("【%s】下【%s】分类不存在", projectId, YaPiService.DEFAULT_CATEGORY_TITLE);
		}
		return categoryByTitle;
	}
}