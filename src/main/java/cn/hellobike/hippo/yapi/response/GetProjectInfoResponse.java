/**
 * Copyright 2021 bejson.com
 */
package cn.hellobike.hippo.yapi.response;

import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import lombok.Data;

import java.util.List;

@Data
public class GetProjectInfoResponse extends BaseResponse {
	private GetProjectInfoEntity data;
}