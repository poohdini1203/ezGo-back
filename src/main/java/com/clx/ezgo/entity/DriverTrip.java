package com.clx.ezgo.entity;

import java.sql.Timestamp;

public class DriverTrip {
    private int id;
    private String driverID;
    private String departure;
    private String destination;
    private String departureCoordinate;
    private String destinationCoordinate;
    private int seatCapacity;
    private int currentPeople;
    private Timestamp departTime;
    private String postscript;
    private int distance;
    private Timestamp createTime;
    private boolean isFinished;
    private Timestamp finishTime;
    private boolean isCanceled;
    private DriverInfo driverInfo;

    public DriverTrip() {
    }

    public DriverTrip( String driverID, String departure, String destination, String departureCoordinate, String destinationCoordinate, int seatCapacity, Timestamp departTime, String postscript,int distance) {
        this.driverID = driverID;
        this.departure = departure;
        this.destination = destination;
        this.departureCoordinate = departureCoordinate;
        this.destinationCoordinate = destinationCoordinate;
        this.seatCapacity = seatCapacity;
        this.departTime = departTime;
        this.postscript = postscript;
        this.distance=distance;
    }
    public DriverTrip(int id, String driverID, String departure, String destination, String departureCoordinate, String destinationCoordinate, int seatCapacity, int currentPeople, Timestamp departTime, String postscript, Timestamp createTime, boolean isFinished, Timestamp finishTime, boolean isCanceled, DriverInfo driverInfo) {
        this.id = id;
        this.driverID = driverID;
        this.departure = departure;
        this.destination = destination;
        this.departureCoordinate = departureCoordinate;
        this.destinationCoordinate = destinationCoordinate;
        this.seatCapacity = seatCapacity;
        this.currentPeople = currentPeople;
        this.departTime = departTime;
        this.postscript = postscript;
        this.createTime = createTime;
        this.isFinished = isFinished;
        this.finishTime = finishTime;
        this.isCanceled = isCanceled;
        this.driverInfo = driverInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureCoordinate(String departureCoordinate) {
        this.departureCoordinate = departureCoordinate;
    }

    public void setDestinationCoordinate(String destinationCoordinate) {
        this.destinationCoordinate = destinationCoordinate;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public void setDepartTime(Timestamp departTime) {
        this.departTime = departTime;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public int getId() {
        return id;
    }

    public String getDriverID() {
        return driverID;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureCoordinate() {
        return departureCoordinate;
    }

    public String getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public Timestamp getDepartTime() {
        return departTime;
    }

    public String getPostscript() {
        return postscript;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }

    public int getCurrentPeople() {
        return currentPeople;
    }

    public void setCurrentPeople(int currentPeople) {
        this.currentPeople = currentPeople;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

