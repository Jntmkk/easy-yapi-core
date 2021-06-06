package cn.hellobike.hippo.yapi.response;

import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import lombok.Data;

import java.util.List;

@Data
public class GetCategoryResponse {
    private List<CategoryEntity> data;
}
