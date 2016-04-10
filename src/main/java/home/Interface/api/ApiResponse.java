package home.Interface.api;

/**
 * Created by qijie on 2016/4/6.
 */
public class ApiResponse {

    /**
     * 结果代码：未通过校验
     */
    public final static String RESULT_CHECK_FAIL = "2";
    /**
     * 结果代码：成功
     */
    public final static String RESULT_SUCCESS = "1";
    /**
     * 结果代码：系统异常
     */
    public final static String RESULT_ERROR = "0";


    /**
     * 结果消息
     */
    private String message;

    /**
     * 结果代码
     */
    private String result;

    public ApiResponse(){}

    public ApiResponse(String message,String result){
        this.message = message;
        this.result = result;
    }

    /**
     * 接口返回异常
     * @param code
     * @param msg
     * @return
     */
    public static ApiResponse fail(String code, String msg) {
        return new ApiResponse(code, msg);
    }

    /**
     * 其他异常
     * @param msg
     * @return
     */
    public static ApiResponse error(String msg) {
        return new ApiResponse(RESULT_ERROR, msg);
    }

    /**
     * 检查异常
     * @param msg
     * @return
     */
    public static ApiResponse checkFail(String msg) {
        return new ApiResponse(RESULT_CHECK_FAIL, msg);
    }
}
