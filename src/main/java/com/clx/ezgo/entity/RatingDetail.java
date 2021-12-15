package com.clx.ezgo.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RatingDetail {
    private int id;
    private String driverID;
    private int driverTripID;
    private String passengerID;
    private int passengerTripID;
    private BigDecimal rating;
    private Timestamp createTime;

    public RatingDetail(String driverID, int driverTripID, String passengerID, int passengerTripID, BigDecimal rating) {
        this.driverID = driverID;
        this.driverTripID = driverTripID;
        this.passengerID = passengerID;
        this.passengerTripID = passengerTripID;
        this.rating = rating;
    }

    public RatingDetail(int id, String driverID, int driverTripID, String passengerID, int passengerTripID, BigDecimal rating, Timestamp createTime) {
        this.id = id;
        this.driverID = driverID;
        this.driverTripID = driverTripID;
        this.passengerID = passengerID;
        this.passengerTripID = passengerTripID;
        this.rating = rating;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public String getDriverID() {
        return driverID;
    }

    public int getDriverTripID() {
        return driverTripID;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public int getPassengerTripID() {
        return passengerTripID;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void setDriverTripID(int driverTripID) {
        this.driverTripID = driverTripID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public void setPassengerTripID(int passengerTripID) {
        this.passengerTripID = passengerTripID;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
