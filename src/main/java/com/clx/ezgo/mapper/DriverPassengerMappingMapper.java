package com.clx.ezgo.mapper;

import com.clx.ezgo.entity.DriverInfo;
import com.clx.ezgo.entity.DriverPassengerMapping;
import com.clx.ezgo.entity.PassengerTrip;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriverPassengerMappingMapper {
    public void addMapping(DriverPassengerMapping mapping);
    public DriverPassengerMapping checkPassengerInvit(DriverPassengerMapping mapping);
    public List<Integer> getPassengerTripsID(int driverTripID);
    public int getDriverTripID(int passengerTripID);
    public List<Integer> getPassengerInvitationsID(int driverTripID);
    public void driverAccept(int driverTripID,int passengerTripID);
    public void driverRefuse(int driverTripID,int passengerTripID);
    public void driverCancel(int driverTripID,int passengerTripID);
    public int getUnFinishedPassengerCount(int driverTripID);
    public void passengerCancel(int driverTripID,int passengerTripID);
    public void passengerWithdrawInvit(int passengerTripID);

}
