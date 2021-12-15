package com.clx.ezgo.mapper;

import com.clx.ezgo.entity.PassengerTrip;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface PassengerTripMapper {
    public void addTrip(PassengerTrip passengerTrip);
    public List<PassengerTrip> getHistoryByPassengerID(String id);
    public List<PassengerTrip> getCurrentTripsByPassengerID(String id);
    public List<PassengerTrip> getAllTripsByPassengerID(String id);
    public List<PassengerTrip> getTripsByCondition(String departure, String destination, Timestamp timeStart,Timestamp timeEnd,int howManyPeople);
    public void tripCancel(int tripID);
    public void confirmPickedUp(int tripID);
    public void confirmFinish(int tripID);
    public PassengerTrip getTripByTripID(int tripID);
    public void setHasDriver(int tripID,boolean hasDriver);
    public int getTotalDistance(String id);
}
