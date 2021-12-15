package com.clx.ezgo.service.impl;

import com.clx.ezgo.entity.*;
import com.clx.ezgo.mapper.*;
import com.clx.ezgo.service.DriverService;
import com.clx.ezgo.service.DriverTripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "driverTripService")
@Service
public class DriverTripServiceImpl implements DriverTripService {

    @Autowired
    DriverTripMapper driverTripMapper;
    @Autowired
    DriverMapper driverMapper;
    @Autowired
    DriverService driverService;
    @Autowired
    DriverPassengerMappingMapper mappingMapper;
    @Autowired
    PassengerTripMapper passengerTripMapper;
    @Autowired
    ReportMapper reportMapper;
    @Override
    public String addTrip(DriverTrip driverTrip) {
        driverTripMapper.addTrip(driverTrip);
        return "0";
    }

    @Override
    public DriverTrip getTripByTripID(int tripID) {
        DriverTrip driverTrip=driverTripMapper.getTripByTripID(tripID);
        if(driverTrip!=null){
            String driverID=driverTrip.getDriverID();
            DriverInfo driverInfo=driverService.getDriverInfoByID(driverID);
            driverTrip.setDriverInfo(driverInfo);
            return driverTrip;
        }
        return null;
    }

    @Override
    public List<DriverTrip> getCurrentTripsByDriverID(String driverID) {
        //没有司机个人信息
        DriverInfo driverInfo=driverMapper.getDriverInfoByID(driverID);
        List<DriverTrip> driverTrips=driverTripMapper.getCurrentTripsByDriverID(driverID);
        List<DriverTrip> res=new ArrayList<>();
        for(DriverTrip dp:driverTrips){
            dp.setDriverInfo(driverInfo);
            res.add(dp);
        }
        return res;
    }

    @Override
    public List<DriverTrip> getHistoryByDriverID(String driverID) {
        DriverInfo driverInfo=driverMapper.getDriverInfoByID(driverID);
        List<DriverTrip> driverTrips=driverTripMapper.getHistoryByDriverID(driverID);
        List<DriverTrip> res=new ArrayList<>();
        for(DriverTrip dp:driverTrips){
            dp.setDriverInfo(driverInfo);
            res.add(dp);
        }
        return res;
    }
    @Override
    public List<DriverTrip> getAllTripsByDriverID(String driverID) {
        DriverInfo driverInfo=driverMapper.getDriverInfoByID(driverID);
        List<DriverTrip> driverTrips=driverTripMapper.getAllTripsByDriver(driverID);
        List<DriverTrip> res=new ArrayList<>();
        for(DriverTrip dp:driverTrips){
            dp.setDriverInfo(driverInfo);
            res.add(dp);
        }
        return res;
    }
    @Override
    public List<DriverTrip> getTripsByCondition(String depart, String dest, Timestamp timeStart, Timestamp timeEnd, String people) {
        String departure="";
        String destination="";
        int howManyPeople=1;
        if(depart!=null&&depart.length()!=0){
            departure=depart;
        }
        if(dest!=null&&destination.length()!=0){
            destination=dest;
        }
        if(people!=null&&people.length()!=0){
            howManyPeople=Integer.valueOf(people);
        }
        List<DriverTrip> driverTrips=driverTripMapper.getTripsByCondition("%"+departure+"%","%"+destination+"%",
                timeStart,timeEnd,howManyPeople);
        List<DriverTrip> res=new ArrayList<>();
        for(DriverTrip dt:driverTrips){
            DriverInfo driverInfo=driverMapper.getDriverInfoByID(dt.getDriverID());
            dt.setDriverInfo(driverInfo);
            res.add(dt);
        }
        return res;
    }

    @Override
    public String cancelTrip(int tripID) {
        //设置车上乘客没司机
        //设置对应关系
        List<Integer> passengerID=mappingMapper.getPassengerTripsID(tripID);
        if(passengerID!=null&&passengerID.size()!=0){
            for(Integer id:passengerID){
                PassengerTrip passengerTrip=passengerTripMapper.getTripByTripID(id);
                //有乘客上车
                if(passengerTrip.isPickedUp()){
                    return "1";
                }
                log.info(id.toString());
                passengerTripMapper.setHasDriver(id,false);
                mappingMapper.driverCancel(tripID,id);
            }
        }
        //设置自身取消
        driverTripMapper.tripCancel(tripID);
       return "0";
    }

    @Override
    public String finishTrip(int tripID) {
        List<Integer> passengerID=mappingMapper.getPassengerTripsID(tripID);
        //只要有一个乘客没结束,都不能完成
        for(Integer id:passengerID){
            PassengerTrip passengerTrip=passengerTripMapper.getTripByTripID(id);
            if(!passengerTrip.isFinished()){
                return "1";
            }
        }
        //乘客全部完成
        driverTripMapper.tripFinish(tripID);
        return "0";
    }

    @Override
    public String reportPassenger(Report report) {
        reportMapper.addReport(report);
        return "0";
    }
}
