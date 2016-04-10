package home.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 * Created by qijie on 2016/3/29.
 */
@Entity
@Table(name = "xf_user")
public class User {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 用户主键（查询用）
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册日期
     */
    private Date   createDate;

    /**
     * 最近登录时间
     */
    private Date   lastLoginDate;

    /**
     * 用户权限
     */
    private Integer role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
