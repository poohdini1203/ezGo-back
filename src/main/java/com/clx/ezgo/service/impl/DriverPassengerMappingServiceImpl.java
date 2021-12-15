package com.clx.ezgo.service.impl;

import com.clx.ezgo.entity.*;
import com.clx.ezgo.mapper.*;
import com.clx.ezgo.service.DriverPassengerMappingService;
import com.clx.ezgo.service.PassengerTripService;
import com.clx.ezgo.utils.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverPassengerMappingServiceImpl implements DriverPassengerMappingService {

    @Autowired
    DriverPassengerMappingMapper MappingMapper;
    @Autowired
    PassengerTripMapper passengerTripMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    DriverTripMapper driverTripMapper;
    @Autowired
    DriverMapper driverMapper;
    @Autowired
    PassengerTripService passengerTripService;
    @Autowired
    DistributedLock distributedLock;

    private final long timeout=10000;
    @Override
    public String driverInvitePassenger(DriverPassengerMapping mapping) {
        int driverTripID=mapping.getDriverTripID();
        int passengerTripID=mapping.getPassengerTripID();
        DriverTrip dt=driverTripMapper.getTripByTripID(driverTripID);
        PassengerTrip pt=passengerTripMapper.getTripByTripID(passengerTripID);
        //检查坐得下么
        if(pt.getHowManyPeople()+dt.getCurrentPeople()>dt.getSeatCapacity()){
            return "1";
        }
        //司机同意
        mapping.setDriverAccept(true);
        //添加对应关系
        MappingMapper.addMapping(mapping);
        //把乘客订单设置为有司机
        passengerTripMapper.setHasDriver(passengerTripID,true);
        //司机车上人数加

        driverTripMapper.updateCurrentPeople(driverTripID,pt.getHowManyPeople());
        return "0";
    }

    @Override
    public String driverTakeOrder(String driverID,PassengerTrip pt) {
        PassengerTrip nowPT=passengerTripMapper.getTripByTripID(pt.getId());
        //检查乘客有没有被接单
        if(nowPT.isHasDriver()){
            return "1";
        }
        //检查乘客有没有取消订单
        if(nowPT.isCanceled()){
            return "2";
        }

        //根据乘客行程新建司机行程,设置当前人数
        DriverTrip dt=new DriverTrip(driverID,pt.getDeparture(),pt.getDestination(),pt.getDepartureCoordinate(),pt.getDestinationCoordinate(),
                                        4,pt.getDepartTime(),"",pt.getDistance());
        driverTripMapper.addTrip(dt);
        int driverTripID=dt.getId();
        int passengerTripID=pt.getId();
        driverTripMapper.updateCurrentPeople(driverTripID,pt.getHowManyPeople());
        DriverPassengerMapping mapping=new DriverPassengerMapping(driverTripID,passengerTripID);
        //司机同意
        mapping.setDriverAccept(true);
        //添加对应关系
        MappingMapper.addMapping(mapping);
        //乘客设置为有司机
        passengerTripMapper.setHasDriver(passengerTripID,true);
        return "0";
    }

    @Override
    public List<PassengerTrip> getPassengersOfDriverTrip(int driverTripID) {
        List<Integer> passengerID=MappingMapper.getPassengerTripsID(driverTripID);
        List<PassengerTrip> res=new ArrayList<>();
        for(Integer id:passengerID){
            PassengerTrip pt=passengerTripService.getTripByTripID(id);
            res.add(pt);
        }
        return res;
    }

    @Override
    public String passengerInviteDriver(DriverPassengerMapping mapping) {
        //添加对应关系
        DriverPassengerMapping check=MappingMapper.checkPassengerInvit(mapping);
        if(check==null){
            MappingMapper.addMapping(mapping);
        }
        return "0";
    }


    @Override
    public String passengerJoinDrvier(String passengerID, DriverTrip dt, int howManyPeople, String postscript) {
        try {
            boolean lock=distributedLock.Lock("joinDriver"+dt.getId());
            if(lock){
                //根据司机行程新建乘客订单
                //添加对应关系
                PassengerTrip pt=new PassengerTrip(passengerID,dt.getDeparture(),dt.getDestination(),dt.getDepartureCoordinate(),
                        dt.getDestinationCoordinate(),howManyPeople,dt.getDepartTime(),postscript,dt.getDistance());
                passengerTripMapper.addTrip(pt);
                int passengerTripID=pt.getId();
                int driverTripID=dt.getId();
                DriverPassengerMapping mapping=new DriverPassengerMapping(driverTripID,passengerTripID);
                //司机同意
                mapping.setDriverAccept(true);
                MappingMapper.addMapping(mapping);
                //司机车上人数更新
                driverTripMapper.updateCurrentPeople(driverTripID,howManyPeople);
                //乘客有司机
                passengerTripMapper.setHasDriver(passengerTripID,true);
                return "0";
            }
        }finally {
            distributedLock.releaseLock("joinDriver"+dt.getId());
        }
        //锁超时
        return "4";
    }

    @Override
    public DriverTrip getDriverTrip(int passengerTripID) {

        int driverTripID=MappingMapper.getDriverTripID(passengerTripID);
        DriverTrip dt=driverTripMapper.getTripByTripID(driverTripID);
        DriverInfo driverInfo=driverMapper.getDriverInfoByID(dt.getDriverID());
        UserInfo userInfo=userInfoMapper.getUserInfoByID(driverInfo.getId());
        driverInfo.setUserInfo(userInfo);
        dt.setDriverInfo(driverInfo);
        return dt;
    }

    @Override
    public List<PassengerTrip> getPassengerInvitations(int driverTripID) {
        List<Integer> ptID=MappingMapper.getPassengerInvitationsID(driverTripID);
        List<PassengerTrip> res=new ArrayList<>();
        for(Integer id:ptID){
                PassengerTrip pt=passengerTripMapper.getTripByTripID(id);
                UserInfo userInfo=userInfoMapper.getUserInfoByID(pt.getPassengerID());
                pt.setUserInfo(userInfo);
                res.add(pt);
        }
        return res;
    }

    @Override
    public String driverAcceptInvitation(DriverPassengerMapping mapping) {

        int driverTripID=mapping.getDriverTripID();
        int passengerTripID=mapping.getPassengerTripID();
        PassengerTrip pt=passengerTripMapper.getTripByTripID(passengerTripID);

        //检查乘客有没有被接单
        if(pt.isHasDriver()){
            return "1";
        }
        //设置司机接受
        //置乘客有司机
        //车上人数修改
        MappingMapper.driverAccept(driverTripID,passengerTripID);
        passengerTripMapper.setHasDriver(passengerTripID,true);
        driverTripMapper.updateCurrentPeople(driverTripID,pt.getHowManyPeople());
        return "0";
    }

    @Override
    public String driverRefuseInvitation(DriverPassengerMapping mapping) {
        int driverTripID=mapping.getDriverTripID();
        int passengerTripID=mapping.getPassengerTripID();
        //司机拒绝
        MappingMapper.driverRefuse(driverTripID,passengerTripID);
        return "0";
    }

    @Override
    public String driverCancelPassengerTrip(DriverPassengerMapping mapping) {
        //设置对应关系司机取消
        int driverTripID=mapping.getDriverTripID();
        int passengerTripID=mapping.getPassengerTripID();
        MappingMapper.driverCancel(driverTripID,passengerTripID);
        //修改车上人数
        PassengerTrip pt=passengerTripMapper.getTripByTripID(passengerTripID);
        driverTripMapper.updateCurrentPeople(driverTripID,-pt.getHowManyPeople());
        //乘客设置没司机
        passengerTripMapper.setHasDriver(passengerTripID,false);
        return "0";
    }
}
