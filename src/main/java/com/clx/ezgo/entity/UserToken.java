package com.clx.ezgo.entity;

import java.io.Serializable;

public class UserToken implements Serializable {
    private String userID;
    private String token;

    public UserToken(String userID, String token) {
        this.userID = userID;
        this.token = token;
    }

    public UserToken() {
    }

    public String getUserID() {
        return userID;
    }

    public String getToken() {
        return token;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userID='" + userID + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
