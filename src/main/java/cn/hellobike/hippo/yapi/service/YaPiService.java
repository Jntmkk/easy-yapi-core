package cn.hellobike.hippo.yapi.service;

import cn.hellobike.hippo.exception.CategoryNotExistException;
import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import cn.hellobike.hippo.yapi.request.AddCategoryRequest;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.response.*;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/27 10:23
 * @Description: 不提供分类，则使用默认分类
 */
public interface YaPiService {
    String DEFAULT_CATEGORY_TITLE = "公共分类";

    /**
     * 新增接口,必须提供名称、分类 ID、path、projectID、service
     * 方法默认为 POST
     *
     * @param request
     * @return
     */
    AddInterfaceResponse addInterface(AddInterfaceRequest request);

    /**
     * 根据 ID 获取接口，不存在返回空
     *
     * @param id
     * @return
     */
    GetInterfaceByIdResponse getInterfaceById(String id);

    /**
     * 添加分类，必须提供分类 title 和 projectID
     *
     * @param request
     * @return
     */
    AddCategoryResponse addCategory(AddCategoryRequest request);

    /**
     * 获得项目信息
     *
     * @param id
     * @return
     */
    GetProjectInfoResponse getProjectInfo(String id);

    /**
     * 更新接口
     *
     * @param request
     * @return
     */
    UpdateInterfaceResponse updateInterface(UpdateInterfaceRequest request);

    /**
     * 根据分类 ID 判断分类是否存在
     *
     * @param id
     * @param projectId
     * @return or Null
     */
    CategoryEntity getCategoryById(String projectId, String id);

    /**
     * 获取所有类别
     *
     * @param projectId
     * @return
     */
    GetCategoryResponse getAllCategory(String projectId);

    /**
     * 根据分类标题判断分类是否存在
     *
     * @param projectId
     * @param title
     * @return
     */
    CategoryEntity containsCategoryByTitle(String projectId, String title);

    /**
     * 根据路径判断接口是否存在
     *
     * @param path
     * @param projectId
     * @return
     */
    GetAllInterfaceEntity containsInterfaceByPath(String projectId, String path);

    /**
     * 获得接口信息，若不存在则创建，若不提供分类则使用公共分类，{@link #DEFAULT_CATEGORY_TITLE}
     *
     * @param request
     * @return
     */
    GetInterfaceByIdResponse getInterfaceOrCreate(AddInterfaceRequest request);

    /**
     * 更新接口，若不存在则创建
     *
     * @param request
     * @return
     */
    UpdateInterfaceResponse updateInterfaceOrCreate(UpdateInterfaceRequest request);

    /**
     * 判断服务是否存在
     *
     * @param serviceName
     * @return
     */
    Boolean containsService(String serviceName);

    /**
     * 根据分类名称获得分类实体，若存在多个返回第一个
     *
     * @param projectId
     * @param title
     * @return
     */
    CategoryEntity getCategoryByTitle(String projectId, String title);

    /**
     * 根据 ID 获取分类，不存在则创建
     *
     * @param projectId
     * @param id
     * @return
     */
    CategoryEntity getCategoryByTitleOrCreate(String projectId, String id);

    /**
     * 获得接口根据 path 获得接口信息，不存在则创建
     *
     * @param request
     * @return
     */
    AddInterfaceResponse getInterfaceByPathOrCreate(AddInterfaceRequest request);

    /**
     * 获得默认分类
     *
     * @param projectId
     * @return
     * @throws CategoryNotExistException
     */
    CategoryEntity getDefaultCategory(String projectId);


    /**
     * 根据分类 ID 获得分类实体
     *
     * @param projectId
     * @param id
     * @return
     */
    CategoryEntity getCategoryByIdOrDefault(String projectId, String id);
}
