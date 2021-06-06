package cn.hellobike.hippo.yapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/27 10:06
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YaPiException extends Exception {
	private Integer errcode;
	private String errmsg;
}