package com.clx.ezgo.entity;

public class UserTripOverall {
    private String id;
    private int totalDistance;
    private int passengerTripN;
    private int driverTripN;

    public UserTripOverall(int totalDistance, int passengerTripN, int driverTripN) {
        this.totalDistance = totalDistance;
        this.passengerTripN = passengerTripN;
        this.driverTripN = driverTripN;
    }

    public UserTripOverall(String id, int totalDistance, int passengerTripN, int driverTripN) {
        this.id = id;
        this.totalDistance = totalDistance;
        this.passengerTripN = passengerTripN;
        this.driverTripN = driverTripN;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getPassengerTripN() {
        return passengerTripN;
    }

    public int getDriverTripN() {
        return driverTripN;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setPassengerTripN(int passengerTripN) {
        this.passengerTripN = passengerTripN;
    }

    public void setDriverTripN(int driverTripN) {
        this.driverTripN = driverTripN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
