package com.clx.ezgo.mapper;

import com.clx.ezgo.entity.DriverTrip;
import com.clx.ezgo.entity.PassengerTrip;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface DriverTripMapper {
    public void addTrip(DriverTrip driverTrip);
    public List<DriverTrip> getCurrentTripsByDriverID(String id);
    public List<DriverTrip> getHistoryByDriverID(String id);
    public List<DriverTrip> getAllTripsByDriver(String driverID);
    public DriverTrip getTripByTripID(int tripID);
    public List<DriverTrip> getTripsByCondition(String departure, String destination, Timestamp timeStart, Timestamp timeEnd, int howManyPeople);
    public void updateCurrentPeople(int tripID,int change);
    public void tripCancel(int tripID);
    public void tripFinish(int tripID);
}
