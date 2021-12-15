package com.clx.ezgo.entity;

import java.math.BigDecimal;

public class RatingOverall {
    private String driverID;
    private BigDecimal averageRating;
    private int rateTimes;

    public RatingOverall(String driverID) {
        this.driverID = driverID;
    }

    public RatingOverall(String driverID, BigDecimal averageRating, int rateTimes) {
        this.driverID = driverID;
        this.averageRating = averageRating;
        this.rateTimes = rateTimes;
    }

    public String getDriverID() {
        return driverID;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public int getRateTimes() {
        return rateTimes;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public void setRateTimes(int rateTimes) {
        this.rateTimes = rateTimes;
    }

    @Override
    public String toString() {
        return "RatingOverall{" +
                "driverID='" + driverID + '\'' +
                ", averageRating=" + averageRating +
                ", rateTimes=" + rateTimes +
                '}';
    }
}
