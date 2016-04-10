package home.Interface.client;

import com.alibaba.fastjson.JSON;
import home.Interface.api.ApiRequest;
import home.Interface.api.ApiRequestBody;
import home.Interface.api.ApiResponse;
import home.Interface.api.ApiHttpResponse;
import home.util.HttpUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * API客服端
 * Created by qijie on 2016/4/6.
 */
public class ApiClient {

    /**
     * 密钥
     */
    private String key = "123456";

    /**
     * 响应时间
     */
    private Integer timeout = 60000;

    /**
     * 接口地址
     */
    private String apiUrl = "http://127.0.0.1:8082/myhome/user-api/api";

    public ApiClient(){}

    public ApiClient(String key,Integer timeout,String apiUrl){
        this.apiUrl = apiUrl;
        this.key = key;
        this.timeout = timeout;
    }

    public <T extends ApiResponse> T send(ApiRequestBody requestBody,
                                        Class<? extends ApiResponse> classResponse){

        StringBuilder pathBuilder = new StringBuilder(apiUrl);
        if(classResponse == null){
            throw new RuntimeException("请求体不能为空");
        }

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.getHeader().setTimestamp(System.currentTimeMillis());
        apiRequest.getHeader().setKey(key);
        apiRequest.setBody(requestBody);

        String msg = JSON.toJSONString(apiRequest);
        String sign = DigestUtils.md5Hex(msg+key);

        StringBuilder paraBuilder = new StringBuilder("msg=");

        try{

            paraBuilder.append(URLEncoder.encode(JSON.toJSONString(apiRequest),"UTF-8"));
            paraBuilder.append("&sign=").append(sign);

        }catch (UnsupportedEncodingException e){
            System.out.print(e.getMessage());
        }

        ApiHttpResponse response = HttpUtils.reqForPost(pathBuilder.toString(),
                paraBuilder.toString(), timeout);
        if (response.getResponseState() == 3){
            return (T) JSON.parseObject("{\"message\":\"请求超时！\",\"result\":\"-1\"}", classResponse);
        }

        return (T) JSON.parseObject(response.getResult(), classResponse);
    }
}
