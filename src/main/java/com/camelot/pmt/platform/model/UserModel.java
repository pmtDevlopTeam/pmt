package com.camelot.pmt.platform.model;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8529564394952754806L;

    /**
     * 用户的索引ID，仅用户查询等业务操作
     */
    private Long id;
    /**
     * 用户唯一32位UUID
     */
    private String userId;
    /**
     * 登陆账号
     */
    private String loginCode;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户状态 0（默认）启用 1 停用 2 锁定'
     */
    private String state;
    /**
     * 用户创建时间
     */
    private Date createTime;
    /**
     * 用户修改时间
     */
    private Date modifyTime;

    public UserModel() {
        super();
    }

    public UserModel(Long id, String userId, String loginCode, String password, String username, String state,
                     Date createTime, Date modifyTime) {
        super();
        this.id = id;
        this.userId = userId;
        this.loginCode = loginCode;
        this.password = password;
        this.username = username;
        this.state = state;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
