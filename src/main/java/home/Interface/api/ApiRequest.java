package home.Interface.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by qijie on 2016/4/6.
 */
public class ApiRequest<T extends ApiRequestBody> {
    /**
     * 请求头
     */
    private ApiRequestHead header;

    /**
     * 请求体
     */
    private T body;

    public static <T extends ApiRequestBody> ApiRequest<T> parseRequest(String json, Class<T> bodyClass) {
        JSONObject jsonObject = JSON.parseObject(json);
        return parseRequest(jsonObject,bodyClass);
    }

    /**
     * 将JSON转换为ApiRequest
     * @param jsonObject
     * @param bodyClass
     * @param <T>
     * @return
     */
    public static <T extends ApiRequestBody> ApiRequest<T> parseRequest(JSONObject jsonObject,Class<T> bodyClass){
        Object body = jsonObject.get("body");
        Object header = jsonObject.get("header");
        ApiRequest<T> apiRequest = new ApiRequest<T>();
        if (header != null && header instanceof JSONObject)
            apiRequest.setHeader(JSONObject.toJavaObject((JSONObject) header, ApiRequestHead.class));
        if (body != null && body instanceof JSONObject)
            apiRequest.setBody(JSONObject.toJavaObject((JSONObject) body, bodyClass));
        return apiRequest;
    }

    public ApiRequestHead getHeader() {
        if(header == null){
            header = new ApiRequestHead();
        }
        return header;
    }

    public void setHeader(ApiRequestHead header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
