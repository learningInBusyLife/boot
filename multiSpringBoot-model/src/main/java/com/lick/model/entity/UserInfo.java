package com.lick.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @description： 用户信息记录表
 * @author: lick
 * @date: 2017年11月13日 15:24
 * @copyright: 版权归 lick 所有
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -6692205767349475680L;
    private String userCode;                   //用户code
    private String userName;                   //用户名称
    private String userSex;                    //用户性别
    private Date   gmtCreate;                  //创建时间
    private Date   gmtModify;                  //修改时间
    private String operator;                   //操作员

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
