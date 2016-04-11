package home.model;

import home.model.page.PageModel;

/**
 * 用户模版（页面展示和远程数据传送）
 * Created by qijie on 2016/3/30.
 */
public class UserModel extends PageModel{

    /**
     * 用户Id，不是User表的主键ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String passWord;

    /**
     * 用户的权限
     */
    private Integer role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
