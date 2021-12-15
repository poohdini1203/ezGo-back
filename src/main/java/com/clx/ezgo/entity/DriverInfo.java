package com.clx.ezgo.entity;

import java.sql.Timestamp;

public class DriverInfo {
    private String id;
    private String plateNumber;
    private String model;
    private String color;
    private String IDCardUrl;
    private String drivingLicenceUrl;
    private String vehicleLicenceUrl;
    private String carImgUrl;
    private Timestamp createTime;
    private boolean isVerified;
    private boolean isActive;
    private UserInfo userInfo;

    public DriverInfo() {
    }

    public DriverInfo(String id, String plateNumber, String model, String color, String IDCardUrl, String drivingLicenceUrl, String vehicleLicenceUrl, String carImgUrl, Timestamp createTime, boolean isVerified, boolean isActive, UserInfo userInfo) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.model = model;
        this.color = color;
        this.IDCardUrl = IDCardUrl;
        this.drivingLicenceUrl = drivingLicenceUrl;
        this.vehicleLicenceUrl = vehicleLicenceUrl;
        this.carImgUrl = carImgUrl;
        this.createTime = createTime;
        this.isVerified = isVerified;
        this.isActive = isActive;
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "DriverInfo{" +
                "id='" + id + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", IDCardUrl='" + IDCardUrl + '\'' +
                ", drivingLicenceUrl='" + drivingLicenceUrl + '\'' +
                ", vehicleLicenceUrl='" + vehicleLicenceUrl + '\'' +
                ", carImgUrl='" + carImgUrl + '\'' +
                ", createTime=" + createTime +
                ", isVerified=" + isVerified +
                ", isActive=" + isActive +
                ", userInfo=" + userInfo +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public String getIDCardUrl() {
        return IDCardUrl;
    }

    public String getDrivingLicenceUrl() {
        return drivingLicenceUrl;
    }

    public String getVehicleLicenceUrl() {
        return vehicleLicenceUrl;
    }

    public String getCarImgUrl() {
        return carImgUrl;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setIDCardUrl(String IDCardUrl) {
        this.IDCardUrl = IDCardUrl;
    }

    public void setDrivingLicenceUrl(String drivingLicenceUrl) {
        this.drivingLicenceUrl = drivingLicenceUrl;
    }

    public void setVehicleLicenceUrl(String vehicleLicenceUrl) {
        this.vehicleLicenceUrl = vehicleLicenceUrl;
    }

    public void setCarImgUrl(String carImgUrl) {
        this.carImgUrl = carImgUrl;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
