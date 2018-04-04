package com.templates.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Table(name = "sys_user")
public class User implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 账号
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户名
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密密码的盐
     */
    private String salt;

    /**
     * 用户状态， 0：未激活， 1：已激活
     */
    @Column(name = "user_status")
    private Boolean userStatus;

    /**
     * 用户类别, 0：普通用户， 1：管理员
     */
    @Column(name = "user_type")
    private Boolean userType;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gtm_modified")
    private Date gtmModified;

    @Transient
    private Set<Role> roles = new HashSet<>();

    private static final long serialVersionUID = 1L;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * 获取主键
     *
     * @return user_id - 主键
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置主键
     *
     * @param userId 主键
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取账号
     *
     * @return login_name - 账号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置账号
     *
     * @param loginName 账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取用户名
     *
     * @return nick_name - 用户名
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户名
     *
     * @param nickName 用户名
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取加密密码的盐
     *
     * @return salt - 加密密码的盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置加密密码的盐
     *
     * @param salt 加密密码的盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取用户状态， 0：未激活， 1：已激活
     *
     * @return user_status - 用户状态， 0：未激活， 1：已激活
     */
    public Boolean getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态， 0：未激活， 1：已激活
     *
     * @param userStatus 用户状态， 0：未激活， 1：已激活
     */
    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取用户类别, 0：普通用户， 1：管理员
     *
     * @return user_type - 用户类别, 0：普通用户， 1：管理员
     */
    public Boolean getUserType() {
        return userType;
    }

    /**
     * 设置用户类别, 0：普通用户， 1：管理员
     *
     * @param userType 用户类别, 0：普通用户， 1：管理员
     */
    public void setUserType(Boolean userType) {
        this.userType = userType;
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gtm_modified
     */
    public Date getGtmModified() {
        return gtmModified;
    }

    /**
     * @param gtmModified
     */
    public void setGtmModified(Date gtmModified) {
        this.gtmModified = gtmModified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", loginName=").append(loginName);
        sb.append(", nickName=").append(nickName);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", userType=").append(userType);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gtmModified=").append(gtmModified);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGtmModified() == null ? other.getGtmModified() == null : this.getGtmModified().equals(other.getGtmModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGtmModified() == null) ? 0 : getGtmModified().hashCode());
        return result;
    }
}