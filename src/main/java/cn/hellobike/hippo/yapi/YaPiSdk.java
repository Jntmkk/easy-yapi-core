package cn.hellobike.hippo.yapi;

import cn.hellobike.hippo.yapi.request.AddCategoryRequest;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.response.*;
import feign.Param;
import feign.RequestLine;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 20:41
 * @Description:
 */
public interface YaPiSdk {
    @RequestLine("POST /api/interface/add")
    AddInterfaceResponse addInterface(AddInterfaceRequest request);

    @RequestLine("GET /api/interface/get?id={id}")
    GetInterfaceByIdResponse getInterfaceById(@Param("id") String id);

    @RequestLine("POST /api/interface/add_cat")
    AddCategoryResponse addCategory(AddCategoryRequest request);

    @RequestLine("GET /api/project/get?id={id}")
    GetProjectInfoResponse getProjectInfo(@Param("id") String id);

    @RequestLine("POST /api/interface/up")
    UpdateInterfaceResponse updateInterface(UpdateInterfaceRequest request);

    @RequestLine("GET /api/interface/list?project_id={project_id}&limit=500&page=1")
    GetAllInterfaceResponse getAllInterface(@Param("project_id") String projectId);

    @RequestLine("GET /gct/team?service={serviceName}")
    GetServiceResponse containsService(@Param("serviceName") String serviceName);

    @RequestLine("GET /api/interface/list_menu?project_id={projectId}")
    GetCategoryResponse getCategoryList(@Param("projectId") String projectId);

}
