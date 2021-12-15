package com.clx.ezgo.service;

import com.clx.ezgo.entity.DriverTrip;
import com.clx.ezgo.entity.PassengerTrip;
import com.clx.ezgo.entity.Report;

import java.sql.Timestamp;
import java.util.List;

public interface DriverTripService {
    public String addTrip(DriverTrip driverTrip);
    public List<DriverTrip> getCurrentTripsByDriverID(String driverID);
    public List<DriverTrip> getHistoryByDriverID(String driverID);
    public List<DriverTrip> getAllTripsByDriverID(String driverID);
    public List<DriverTrip> getTripsByCondition(String departure, String destination, Timestamp timeStart, Timestamp timeEnd, String howManyPeople);
    public DriverTrip getTripByTripID(int tripID);
    public String cancelTrip(int tripID);
    public String finishTrip(int tripID);
    public String reportPassenger(Report report);
}
