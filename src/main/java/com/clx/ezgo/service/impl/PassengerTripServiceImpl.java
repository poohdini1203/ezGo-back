package com.clx.ezgo.service.impl;

import com.clx.ezgo.entity.*;
import com.clx.ezgo.mapper.*;
import com.clx.ezgo.service.PassengerTripService;
import com.clx.ezgo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerTripServiceImpl implements PassengerTripService {
    @Autowired
    PassengerTripMapper passengerTripMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    DriverPassengerMappingMapper mappingMapper;
    @Autowired
    DriverTripMapper driverTripMapper;
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    RatingDetailMapper ratingDetailMapper;
    @Autowired
    RatingOverallMapper ratingOverallMapper;

    @Override
    public String addTrip(PassengerTrip passengerTrip) {
        passengerTripMapper.addTrip(passengerTrip);
        return "0";
    }

    @Override
    public List<PassengerTrip> getHistoryByPassengerID(String passengerID) {
    //按时间排序，近的在前
        UserInfo userInfo=userInfoMapper.getUserInfoByID(passengerID);
        List<PassengerTrip> passengerTrips=passengerTripMapper.getHistoryByPassengerID(passengerID);
        List<PassengerTrip> res=new ArrayList<>();
        for(int i=0;i<passengerTrips.size();i++){
            PassengerTrip PT=passengerTrips.get(i);
            PT.setUserInfo(userInfo);
            res.add(PT);
        }
        return res;
    }

    @Override
    public List<PassengerTrip> getCurrentTripsByPassengerID(String passengerID) {
        UserInfo userInfo=userInfoMapper.getUserInfoByID(passengerID);
        List<PassengerTrip> passengerTrips=passengerTripMapper.getCurrentTripsByPassengerID(passengerID);
        List<PassengerTrip> res=new ArrayList<>();
        for(int i=0;i<passengerTrips.size();i++){
            PassengerTrip PT=passengerTrips.get(i);
            PT.setUserInfo(userInfo);
            res.add(PT);
        }
        return res;
    }

    @Override
    public List<PassengerTrip> getAllTripsByPassengerID(String passengerID) {
        UserInfo userInfo=userInfoMapper.getUserInfoByID(passengerID);
        List<PassengerTrip> passengerTrips=passengerTripMapper.getAllTripsByPassengerID(passengerID);
        List<PassengerTrip> res=new ArrayList<>();
        for(int i=0;i<passengerTrips.size();i++){
            PassengerTrip PT=passengerTrips.get(i);
            PT.setUserInfo(userInfo);
            res.add(PT);
        }
        return res;
    }

    @Override
    public List<PassengerTrip> getTripsByCondition(String depart, String dest, Timestamp timeStart, Timestamp timeEnd, String people) {

        String departure="";
        String destination="";
        int howManyPeople=4;
        if(depart!=null&&depart.length()!=0){
            departure=depart;
        }
        if(dest!=null&&destination.length()!=0){
            destination=dest;
        }
        if(people!=null&&people.length()!=0){
            howManyPeople=Integer.valueOf(people);
        }
        List<PassengerTrip> passengerTrips=passengerTripMapper.getTripsByCondition("%"+departure+"%","%"+destination+"%",
                                                                                    timeStart,timeEnd,howManyPeople);
        List<PassengerTrip> res=new ArrayList<>();
        for(PassengerTrip pt:passengerTrips){
            UserInfo userInfo=userInfoMapper.getUserInfoByID(pt.getPassengerID());
            pt.setUserInfo(userInfo);
            res.add(pt);
        }
        return res;
    }

    @Override
    public PassengerTrip getTripByTripID(int tripID) {
        PassengerTrip passengerTrip=passengerTripMapper.getTripByTripID(tripID);
        if(passengerTrip!=null){
            String passengerID=passengerTrip.getPassengerID();
            UserInfo userInfo=userInfoMapper.getUserInfoByID(passengerID);
            passengerTrip.setUserInfo(userInfo);
            return passengerTrip;
        }
        return null;
    }


    @Override
    public String tripCancel(int tripID) {
        //设置自身取消
        PassengerTrip check=passengerTripMapper.getTripByTripID(tripID);
        if(check!=null){
            passengerTripMapper.tripCancel(tripID);
            //如果有司机
            if(check.isHasDriver()==true){
                int driverTripID=mappingMapper.getDriverTripID(tripID);
                //司机乘客数量更新
                driverTripMapper.updateCurrentPeople(driverTripID,-check.getHowManyPeople());
                //设置对应关系
                mappingMapper.passengerCancel(driverTripID,check.getId());
            }
            //撤回邀请
            mappingMapper.passengerWithdrawInvit(tripID);
            return "0";
        }
        return "1";
    }

    @Override
    public String confirmPickedUp(int tripID) {
        PassengerTrip check=passengerTripMapper.getTripByTripID(tripID);
        if(check!=null){
            passengerTripMapper.confirmPickedUp(tripID);
            return "0";
        }
        return "1";
    }

    @Override
    public String confirmFinish(int tripID) {
        PassengerTrip check=passengerTripMapper.getTripByTripID(tripID);
        if(check!=null){
            //自身设置结束
            passengerTripMapper.confirmFinish(tripID);
            return "0";
        }
        return "1";
    }

    @Override
    public String reportDriver(Report report) {
        reportMapper.addReport(report);
        return "0";
    }

    @Override
    public String rateDriver(RatingDetail ratingDetail) {
        //添加详细评分记录
        ratingDetailMapper.add(ratingDetail);
        String driverID=ratingDetail.getDriverID();
        //修改总体评分
        RatingOverall overall=ratingOverallMapper.get(driverID);
        System.out.println(overall.toString());
        BigDecimal average=overall.getAverageRating();
        int times=overall.getRateTimes();
        int newTimes=times+1;
        //(原来的总分+这次评分)/新的评价次数
        BigDecimal newAverage=(average.multiply(new BigDecimal(times)).add(ratingDetail.getRating())).divide(new BigDecimal(newTimes),BigDecimal.ROUND_HALF_UP);
        RatingOverall newOverall=new RatingOverall(driverID,newAverage,newTimes);
        System.out.println(newOverall.toString());
        ratingOverallMapper.update(newOverall);
        return "0";
    }
}
