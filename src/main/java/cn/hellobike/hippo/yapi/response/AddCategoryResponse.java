package cn.hellobike.hippo.yapi.response;

import cn.hellobike.hippo.yapi.entity.AddCategoryResponseEntity;
import cn.hellobike.hippo.yapi.entity.CategoryEntity;
import lombok.Data;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 21:25
 * @Description:
 */
@Data
public class AddCategoryResponse extends BaseResponse {
    private CategoryEntity data;
}