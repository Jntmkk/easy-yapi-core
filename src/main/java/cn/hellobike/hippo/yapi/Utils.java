package cn.hellobike.hippo.yapi;

import cn.hellobike.hippo.exception.YaPiException;
import cn.hellobike.hippo.processor.BaseContext;
import cn.hellobike.hippo.yapi.entity.GetInterfaceByIdResponseEntity;
import cn.hellobike.hippo.yapi.parse.DefaultJacksonDecoder;
import cn.hellobike.hippo.yapi.request.AddInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateInterfaceRequest;
import cn.hellobike.hippo.yapi.request.UpdateOrCreateRequest;
import cn.hellobike.hippo.yapi.response.UpdateOrCreateResponse;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.*;
import feign.Feign;
import feign.RequestInterceptor;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: yuewenbo971@hellobike.com
 * @Date: 2021/5/26 17:44
 * @Description:
 */
@Slf4j
public class Utils {
    public static List<RequestInterceptor> interceptors = new LinkedList<>();

    public static void addRequestInterceptor(RequestInterceptor interceptor) {
        interceptors.add(interceptor);
    }

    public static YaPiSdk getDefaultYaPiSDK(String host, String token) {
        YaPiSdk yaPiSdk = Feign.builder().encoder(new JacksonEncoder()).decoder(new DefaultJacksonDecoder()).requestInterceptor(requestTemplate ->
                {
                    requestTemplate.header("Content-Type", "application/json;charset=UTF-8");
                    requestTemplate.query("token", token);
                    log.info(JSONObject.toJSONString(requestTemplate.getRequestVariables()));
                }
        ).target(YaPiSdk.class, host);
        return yaPiSdk;
    }

    public static String getModifyDescription(String before, String current) throws JsonProcessingException {
        StringBuilder builder = new StringBuilder();
        return builder.toString();
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

    public static UpdateOrCreateResponse sendRequest(VelocityContext context, BaseContext baseContext) throws Exception {
        UpdateOrCreateRequest request = getRequestFromVelocityTemplate(context);
        return baseContext.getYaPiService().updateOrCreate(request);
    }

    public static UpdateOrCreateRequest getRequestFromVelocityTemplate(VelocityContext context) throws IOException {
        Velocity.init();
        InputStream resourceAsStream = Utils.class.getClass().getClassLoader().getResourceAsStream("request.json");
        String template = IOUtils.toString(resourceAsStream, "utf8");
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "tag", template);
        String target = writer.toString();
        UpdateOrCreateRequest updateOrCreateRequest = JSONObject.parseObject(target, UpdateOrCreateRequest.class);
        return updateOrCreateRequest;
    }
}