package com.clx.ezgo.entity;

import java.sql.Timestamp;

public class UserInfo {
    private String id;
    private String name;
    private String avatarUrl;
    private String nickName;
    private String IDCard;
    private String cellphone;
    private Timestamp createTime;
    private boolean isForbidden;


    public UserInfo() {
    }

    public UserInfo(String id, String name, String avatarUrl, String nickName, String IDCard, String cellphone, Timestamp createTime, boolean isForbidden) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.nickName = nickName;
        this.IDCard = IDCard;
        this.cellphone = cellphone;
        this.createTime = createTime;
        this.isForbidden = isForbidden;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public String getIDCard() {
        return IDCard;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public boolean isForbidden() {
        return isForbidden;
    }

    public void setForbidden(boolean forbidden) {
        isForbidden = forbidden;
    }
}
