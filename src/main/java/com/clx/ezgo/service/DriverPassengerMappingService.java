package com.clx.ezgo.service;

import com.clx.ezgo.entity.DriverInfo;
import com.clx.ezgo.entity.DriverPassengerMapping;
import com.clx.ezgo.entity.DriverTrip;
import com.clx.ezgo.entity.PassengerTrip;

import java.util.List;

public interface DriverPassengerMappingService {
    public String driverInvitePassenger(DriverPassengerMapping driverPassengerMapping);
    public String driverTakeOrder(String driverID,PassengerTrip passengerTrip);
    public List<PassengerTrip> getPassengersOfDriverTrip(int driverTripID);
    public List<PassengerTrip> getPassengerInvitations(int driverTripID);
    public String driverAcceptInvitation(DriverPassengerMapping driverPassengerMapping);
    public String driverRefuseInvitation(DriverPassengerMapping driverPassengerMapping);
    public String driverCancelPassengerTrip(DriverPassengerMapping driverPassengerMapping);

    public String passengerInviteDriver(DriverPassengerMapping driverPassengerMapping);
    public String passengerJoinDrvier(String passengerID, DriverTrip driverTrip,int howManyPeople,String postscript);
    public DriverTrip getDriverTrip(int passengerTripID);
}
