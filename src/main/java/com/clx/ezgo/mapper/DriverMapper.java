package com.clx.ezgo.mapper;

import com.clx.ezgo.entity.DriverInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriverMapper {
    public void addDriver(String id,String model,String plate,String color);
    public DriverInfo getDriverInfoByID(String id);
    public List<DriverInfo> getDriverRequests();
    public void updateIDCardImg(String id,String url);
    public void updateDrivingLicence(String id,String url);
    public void updateVehicleLicence(String id,String url);
    public void updateCarImg(String id,String url);
    public void setVerified(String id);
    public void setActive(String id,boolean active);
}
