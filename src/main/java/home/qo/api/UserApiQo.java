package home.qo.api;

import home.qo.ApiPageQo;

/**
 * Created by qijie on 2016/4/6.
 */
public class UserApiQo extends ApiPageQo{

    /**
     * 用户名称
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
