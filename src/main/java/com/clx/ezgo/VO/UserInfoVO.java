package com.clx.ezgo.VO;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfoVO {
    private String id;
    private String name;
    private String avatarUrl;
    private String nickName;
    @JSONField(name = "IDCard")
    private String IDCard;
    private String cellphone;

    public UserInfoVO() {
    }

    public UserInfoVO(String id, String name, String avatarUrl, String nickName, String IDCard, String cellphone) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.nickName = nickName;
        this.IDCard = IDCard;
        this.cellphone = cellphone;
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
}
