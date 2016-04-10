package home.Interface.api;

/**
 * 请求头
 * Created by qijie on 2016/4/6.
 */
public class ApiRequestHead {
    /**
     * 请求方法名称
     */
    private  String actionName;

    /**
     * 请求签名
     */
    private String sign;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 时间戳
     */
    private long timestamp;

    /**
     * Key
     */
    private String  key;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
