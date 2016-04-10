package home.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import home.Interface.api.ApiRequest;
import home.Interface.api.ApiResponse;
import home.exception.ShowMessageException;
import home.qo.api.UserApiQo;
import home.service.api.UserApiService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by qijie on 2016/4/6.
 */
@Controller
@RequestMapping("/user-api")
public class UserApiController {

    @Autowired
    private UserApiService  userApiService;

    @RequestMapping("/api")
    @ResponseBody
    public String api(HttpServletRequest request,
                      String msg,String sign){

        //验证签名
        checkMsg(request,msg,sign);

        ApiResponse apiResponse = null;

        try {
            apiResponse = executeAction(msg);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(apiResponse);
    }

    /**
     * 验证签名
     * @param msg
     * @param sign
     */
    private void checkMsg(HttpServletRequest request,String msg,String sign){
        JSONObject jsonObject = null;

        try {
            jsonObject = JSONObject.parseObject(msg);
        } catch (Exception e) {
            throw new ShowMessageException("非法的请求体");
        }

        if (jsonObject == null)
            throw new ShowMessageException("非法的请求体");

        if (sign == null)
            throw new ShowMessageException("非法的请求体");

        JSONObject header = (JSONObject) jsonObject.get("header");
        JSONObject body = (JSONObject) jsonObject.get("body");

        if (header == null)
            throw new ShowMessageException("请求体头部不能为空");

        if (body == null)
            throw new ShowMessageException("请求体不能为空");

        String key = (String) header.get("key");

        if (key == null || key.trim().length() == 0)
            throw new ShowMessageException("key不能为空");

        if (!StringUtils.equalsIgnoreCase(sign, DigestUtils.md5Hex(msg+key)))
            throw new ShowMessageException("签名认证未通过");
    }

    /**
     * 执行请求
     * @param requestJson
     * @return
     * @throws InvocationTargetException
     */
    private ApiResponse executeAction(String requestJson) throws InvocationTargetException {

        try {
            return userApiService.queryUser(ApiRequest.parseRequest(requestJson,UserApiQo.class));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return ApiResponse.error("未知异常");
    }
}
