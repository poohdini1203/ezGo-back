package com.clx.ezgo.service;

import com.clx.ezgo.entity.DriverInfo;
import com.clx.ezgo.entity.Report;

import java.util.List;

public interface AdminService {
    public String verifyAdmin(String id);
    public List<DriverInfo> getDriverRequests();
    public  String passRequest(String driverID);
    public String rejectRequest(String driverID);
    public String forbidUser(String id);
    public String activeUser(String id);
    public List<Report> getReportByUser(String id);
}
