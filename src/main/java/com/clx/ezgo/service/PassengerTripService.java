package com.clx.ezgo.service;

import com.clx.ezgo.entity.PassengerTrip;
import com.clx.ezgo.entity.RatingDetail;
import com.clx.ezgo.entity.Report;

import java.sql.Timestamp;
import java.util.List;

public interface PassengerTripService {
    public String addTrip(PassengerTrip passengerTrip);
    public List<PassengerTrip> getHistoryByPassengerID(String passengerID);
    public List<PassengerTrip> getCurrentTripsByPassengerID(String passengerID);
    public List<PassengerTrip> getAllTripsByPassengerID(String passengerID);
    public List<PassengerTrip> getTripsByCondition(String departure, String destination, Timestamp timeStart, Timestamp timeEnd, String howManyPeople);
    public PassengerTrip getTripByTripID(int tripID);
    public String tripCancel(int tripID);
    public String confirmPickedUp(int tripID);
    public String confirmFinish(int tripID);
    public String reportDriver(Report report);
    public String rateDriver(RatingDetail ratingDetail);
}
