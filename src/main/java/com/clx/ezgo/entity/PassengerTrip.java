package com.clx.ezgo.entity;

import java.sql.Timestamp;

public class PassengerTrip {
    private int id;
    private String passengerID;
    private String departure;
    private String destination;
    private String departureCoordinate;
    private String destinationCoordinate;
    private int howManyPeople;
    private Timestamp departTime;
    private String postscript;
    private int distance;
    private Timestamp createTime;
    private boolean hasDriver;
    private boolean isPickedUp;
    private Timestamp pickUpTime;
    private boolean isFinished;
    private  Timestamp finishTime;
    private boolean isCanceled;
    private UserInfo userInfo;
    
    public PassengerTrip() {
    }
    public PassengerTrip(int id, String passengerID, String departure, String destination, String departureCoordinate, String destinationCoordinate, int howManyPeople, Timestamp departTime, String postscript, Timestamp createTime, boolean hasDriver, boolean isPickedUp, Timestamp pickUpTime, boolean isFinished, Timestamp finishTime, boolean isCanceled, UserInfo userInfo) {
        this.id = id;
        this.passengerID = passengerID;
        this.departure = departure;
        this.destination = destination;
        this.departureCoordinate = departureCoordinate;
        this.destinationCoordinate = destinationCoordinate;
        this.howManyPeople = howManyPeople;
        this.departTime = departTime;
        this.postscript = postscript;
        this.createTime = createTime;
        this.hasDriver = hasDriver;
        this.isPickedUp = isPickedUp;
        this.pickUpTime = pickUpTime;
        this.isFinished = isFinished;
        this.finishTime = finishTime;
        this.isCanceled = isCanceled;
        this.userInfo = userInfo;
    }
    public PassengerTrip(String passengerID, String departure, String destination, String departureCoordinate, String destinationCoordinate, int howManyPeople, Timestamp departTime, String postscript,int distance) {
        this.passengerID = passengerID;
        this.departure = departure;
        this.destination = destination;
        this.departureCoordinate = departureCoordinate;
        this.destinationCoordinate = destinationCoordinate;
        this.howManyPeople = howManyPeople;
        this.departTime = departTime;
        this.postscript = postscript;
        this.distance=distance;
    }

    public int getId() {
        return id;
    }

    public String getPassengerID() {
        return passengerID;
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

    public int getHowManyPeople() {
        return howManyPeople;
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

    public boolean isHasDriver() {
        return hasDriver;
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public Timestamp getPickUpTime() {
        return pickUpTime;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
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

    public void setHowManyPeople(int howManyPeople) {
        this.howManyPeople = howManyPeople;
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

    public void setHasDriver(boolean hasDriver) {
        this.hasDriver = hasDriver;
    }

    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }

    public void setPickUpTime(Timestamp pickUpTime) {
        this.pickUpTime = pickUpTime;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }



    @Override
    public String toString() {
        return "PassengerTrip{" +
                "id=" + id +
                ", passengerID='" + passengerID + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureCoordinate='" + departureCoordinate + '\'' +
                ", destinationCoordinate='" + destinationCoordinate + '\'' +
                ", howManyPeople=" + howManyPeople +
                ", departTime=" + departTime +
                ", postscript='" + postscript + '\'' +
                ", createTime=" + createTime +
                ", hasDriver=" + hasDriver +
                ", isPickedUp=" + isPickedUp +
                ", pickUpTime=" + pickUpTime +
                ", isFinished=" + isFinished +
                ", finishTime=" + finishTime +
                ", isCanceled=" + isCanceled +
                ", userInfo=" + userInfo +
                '}';
    }
}
