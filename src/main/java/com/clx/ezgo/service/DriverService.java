package com.clx.ezgo.service;

import com.clx.ezgo.entity.DriverInfo;
import com.clx.ezgo.entity.RatingOverall;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface DriverService {
    public String addDriver(String id,String model,String plate,String color);
    public DriverInfo getDriverInfoByID(String id);
    public void uploadImg(String id,String category,String url);
    public RatingOverall getRatingOverall(String driverID);
}
