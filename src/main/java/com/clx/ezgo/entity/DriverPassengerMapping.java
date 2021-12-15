package com.clx.ezgo.entity;

public class DriverPassengerMapping {
    private int id;
    private int driverTripID;
    private int passengerTripID;
    private boolean driverAccept;
    private boolean driverRefuse;
    private boolean driverCancel;
    private boolean passengerCancel;

    public DriverPassengerMapping() {
    }

    public DriverPassengerMapping( int driverTripID, int passengerTripID) {
        this.driverTripID = driverTripID;
        this.passengerTripID = passengerTripID;
    }

    public DriverPassengerMapping(int id, int driverTripID, int passengerTripID, boolean driverAccept, boolean driverRefuse, boolean driverCancel, boolean passengerCancel) {
        this.id = id;
        this.driverTripID = driverTripID;
        this.passengerTripID = passengerTripID;
        this.driverAccept = driverAccept;
        this.driverRefuse = driverRefuse;
        this.driverCancel = driverCancel;
        this.passengerCancel = passengerCancel;
    }

    public int getId() {
        return id;
    }

    public int getDriverTripID() {
        return driverTripID;
    }

    public int getPassengerTripID() {
        return passengerTripID;
    }

    public boolean isDriverAccept() {
        return driverAccept;
    }

    public boolean isDriverRefuse() {
        return driverRefuse;
    }

    public boolean isDriverCancel() {
        return driverCancel;
    }

    public boolean isPassengerCancel() {
        return passengerCancel;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDriverTripID(int driverTripID) {
        this.driverTripID = driverTripID;
    }

    public void setPassengerTripID(int passengerTripID) {
        this.passengerTripID = passengerTripID;
    }

    public void setDriverAccept(boolean driverAccept) {
        this.driverAccept = driverAccept;
    }

    public void setDriverRefuse(boolean driverRefuse) {
        this.driverRefuse = driverRefuse;
    }

    public void setDriverCancel(boolean driverCancel) {
        this.driverCancel = driverCancel;
    }

    public void setPassengerCancel(boolean passengerCancel) {
        this.passengerCancel = passengerCancel;
    }
}
