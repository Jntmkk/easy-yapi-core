package cn.hellobike.hippo.yapi;

import cn.hellobike.hippo.FlatMapUtils;
import cn.hellobike.hippo.yapi.entity.GetInterfaceByIdResponseEntity;
import cn.hellobike.hippo.yapi.func.Fun1;
import cn.hellobike.hippo.yapi.parse.DefaultJacksonDecoder;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.response.AddInterfaceResponse;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.*;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 17:44
 * @Description:
 */
public class Utils {
	public static YaPiSdk getDefaultYaPiSDK(String host, String token) {
		YaPiSdk yaPiSdk = Feign.builder().encoder(new JacksonEncoder()).decoder(new DefaultJacksonDecoder()).requestInterceptor(requestTemplate ->
				{
					requestTemplate.header("Content-Type", "application/json;charset=UTF-8");
					requestTemplate.query("token", token);
				}
		).target(YaPiSdk.class, host);
		return yaPiSdk;
	}

	public static String getModifyDescription(String before, String current) throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		TypeReference<HashMap<String, Object>> type =
//				new TypeReference<HashMap<String, Object>>() {
//				};
//		Map<String, Object> leftMap = mapper.readValue(before, type);
//		Map<String, Object> rightMap = mapper.readValue(current, type);
//		Map<String, Object> leftFlatMap = FlatMapUtils.flatten(leftMap);
//		Map<String, Object> rightFlatMap = FlatMapUtils.flatten(rightMap);
//
//		MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);
		StringBuilder builder = new StringBuilder();
//		difference.entriesDiffering().forEach((k, v) -> builder.append(k + "--" + v + ","));
//		if (builder.length() > 0) {
//			builder.deleteCharAt(builder.length() - 1);
//		}
		return builder.toString();
	}

	public static String getModifyDescription(Fun1 f1, Fun1 f2) throws JsonProcessingException {
		String s1 = f1.fun1();
		String s2 = f2.fun1();
		return getModifyDescription(s1, s2);
	}

	public static String getClassJasonSchema(Class cls) {
		SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON);
		SchemaGeneratorConfig config = configBuilder.build();
		SchemaGenerator generator = new SchemaGenerator(config);
		JsonNode jsonSchema = generator.generateSchema(cls);
		return jsonSchema.toString();
	}

	public static UpdateInterfaceRequest mapGetResponseToUpdateRequest(GetInterfaceByIdResponseEntity entity) {
		UpdateInterfaceRequest request = new UpdateInterfaceRequest();
		BeanUtil.copyProperties(entity, request, CopyOptions.create().ignoreNullValue().ignoreCase());
		request.setReq_body_form(entity.getReq_body_form());
		request.setReq_headers(entity.getReq_headers());
		request.setReq_params(entity.getReq_params());
		request.setReq_query(entity.getReq_query());
		request.setReq_body_is_json_schema(true);
		request.setReq_body_is_json_schema(true);
		request.setId(entity.get_id());
		return request;
	}

	public static AddInterfaceRequest mapUpdateRequestToAdd(UpdateInterfaceRequest request) {
		AddInterfaceRequest request1 = new AddInterfaceRequest();
		BeanUtil.copyProperties(request, request1, true);
		return request1;
	}

	public static void updateUpdateRequest(UpdateInterfaceRequest before, UpdateInterfaceRequest cur) {
		BeanUtil.copyProperties(cur, before, CopyOptions.create().ignoreNullValue().ignoreCase());
		if (cur.getReq_body_form() != null) {
			before.setReq_body_form(cur.getReq_body_form());
		}
		if (cur.getReq_params() != null) {
			before.setReq_params(cur.getReq_params());
		}
		if (cur.getReq_headers() != null) {
			before.setReq_headers(cur.getReq_headers());
		}
		if (cur.getReq_query() != null) {
			before.setReq_query(cur.getReq_query());
		}
	}
}