package com.luxsuen.requestjson.entity;

import java.util.Date;

public class User {
    private Integer userId;

    private String userAccount;

    private String userPassword;

    private String userRemark;

    private Byte userDel;

    private Date userCreatetime;

    private Date userUpdatetime;

    public User(Integer userId, String userAccount, String userPassword, String userRemark, Byte userDel, Date userCreatetime, Date userUpdatetime) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userRemark = userRemark;
        this.userDel = userDel;
        this.userCreatetime = userCreatetime;
        this.userUpdatetime = userUpdatetime;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
    }

    public Byte getUserDel() {
        return userDel;
    }

    public void setUserDel(Byte userDel) {
        this.userDel = userDel;
    }

    public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }

    public Date getUserUpdatetime() {
        return userUpdatetime;
    }

    public void setUserUpdatetime(Date userUpdatetime) {
        this.userUpdatetime = userUpdatetime;
    }
}