package com.clx.ezgo.service.impl;

import com.clx.ezgo.entity.DriverInfo;
import com.clx.ezgo.entity.Report;
import com.clx.ezgo.entity.UserInfo;
import com.clx.ezgo.mapper.AdminMapper;
import com.clx.ezgo.mapper.DriverMapper;
import com.clx.ezgo.mapper.ReportMapper;
import com.clx.ezgo.mapper.UserInfoMapper;
import com.clx.ezgo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    DriverMapper driverMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    ReportMapper reportMapper;
    @Override
    public String verifyAdmin(String id) {
        int check=adminMapper.findAdmin(id);
        if(check==1){
            return "0";
        }
        return "1";
    }

    @Override
    public List<DriverInfo> getDriverRequests() {
        List<DriverInfo> driverInfos=driverMapper.getDriverRequests();
        List<DriverInfo> res=new ArrayList<>();
        for(DriverInfo driverInfo:driverInfos){
            UserInfo userInfo=userInfoMapper.getUserInfoByID(driverInfo.getId());
            driverInfo.setUserInfo(userInfo);
            res.add(driverInfo);
        }
        return res;
    }

    @Override
    public String passRequest(String driverID) {
        driverMapper.setVerified(driverID);
        driverMapper.setActive(driverID,true);
        return "0";
    }

    @Override
    public String rejectRequest(String driverID) {
        driverMapper.setVerified(driverID);
        driverMapper.setActive(driverID,false);
        return "0";
    }

    @Override
    public String forbidUser(String id) {
        UserInfo check=userInfoMapper.getUserInfoByID(id);
        if(check!=null){
            userInfoMapper.forbidUser(id);
            return "0";
        }
        return "1";
    }

    @Override
    public String activeUser(String id) {
        UserInfo check=userInfoMapper.getUserInfoByID(id);
        if(check!=null){
            userInfoMapper.activeUser(id);
            return "0";
        }
        return "1";
    }

    @Override
    public List<Report> getReportByUser(String id) {
        UserInfo check=userInfoMapper.getUserInfoByID(id);
        if(check!=null){
            return reportMapper.selectReportByUser(id);
        }
        return null;
    }
}
