package com.clx.ezgo.entity;

import java.sql.Timestamp;

public class Report {
    private int id;
    private String receiverID;
    private int receiverTripID;
    private String repoterID;
    private int repoterTripID;
    private int type;
    private String content;
    private Timestamp createTime;

    public Report(String receiverID, int receiverTripID, String repoterID, int repoterTripID, int type, String content) {
        this.receiverID = receiverID;
        this.receiverTripID = receiverTripID;
        this.repoterID = repoterID;
        this.repoterTripID = repoterTripID;
        this.type = type;
        this.content = content;
    }

    public Report(int id, String receiverID, int receiverTripID, String repoterID, int repoterTripID, int type, String content, Timestamp createTime) {
        this.id = id;
        this.receiverID = receiverID;
        this.receiverTripID = receiverTripID;
        this.repoterID = repoterID;
        this.repoterTripID = repoterTripID;
        this.type = type;
        this.content = content;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public int getReceiverTripID() {
        return receiverTripID;
    }

    public String getRepoterID() {
        return repoterID;
    }

    public int getRepoterTripID() {
        return repoterTripID;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public void setReceiverTripID(int receiverTripID) {
        this.receiverTripID = receiverTripID;
    }

    public void setRepoterID(String repoterID) {
        this.repoterID = repoterID;
    }

    public void setRepoterTripID(int repoterTripID) {
        this.repoterTripID = repoterTripID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
