package com.clx.ezgo.service.impl;

import com.clx.ezgo.entity.DriverInfo;
import com.clx.ezgo.entity.RatingOverall;
import com.clx.ezgo.entity.UserInfo;
import com.clx.ezgo.mapper.DriverMapper;
import com.clx.ezgo.mapper.RatingOverallMapper;
import com.clx.ezgo.mapper.UserInfoMapper;
import com.clx.ezgo.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "司机信息")
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverMapper driverMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RatingOverallMapper ratingOverallMapper;
    @Override
    public String addDriver(String id,String model,String plate,String color) {
        DriverInfo check=getDriverInfoByID(id);
        //没申请过或管理员看过
        if(check==null||check.isVerified()==true){
            driverMapper.addDriver(id,model,plate,color);
            //设置总行程数
            RatingOverall ratingOverall=new RatingOverall(id,new BigDecimal(0),0);
            ratingOverallMapper.add(ratingOverall);
            return "0";
        }
        return "1";
    }

    @Override
    public DriverInfo getDriverInfoByID(String id) {
        DriverInfo driverInfo=driverMapper.getDriverInfoByID(id);
        if(driverInfo!=null){
            //查询用户信息
            driverInfo.setUserInfo(userInfoMapper.getUserInfoByID(id));
            return driverInfo;
        }
        return null;
    }



    @Override
    public void uploadImg(String id, String category, String url) {
        DriverInfo check=getDriverInfoByID(id);

        if(check!=null){

            log.info(category);
            switch (category){
                case "IDCard":

                    driverMapper.updateIDCardImg(id,url);
                    break;


                case "drivingLicence":

                    driverMapper.updateDrivingLicence(id,url);
                    break;


                case "vehicleLicence":

                    driverMapper.updateVehicleLicence(id,url);
                    break;


                case "carImg":

                    driverMapper.updateCarImg(id,url);
                    break;

            }
        }
    }

    @Override
    public RatingOverall getRatingOverall(String driverID) {
        RatingOverall ratingOverall=ratingOverallMapper.get(driverID);
        BigDecimal rating=ratingOverall.getAverageRating();
        ratingOverall.setAverageRating(new BigDecimal(String.valueOf(rating)).setScale(1,BigDecimal.ROUND_HALF_UP));
        System.out.println(rating);
        return ratingOverall;
    }

    public static void main(String[] args) {

    }
}
