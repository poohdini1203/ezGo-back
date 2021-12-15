package com.clx.ezgo.controller;

import com.alibaba.fastjson.JSONObject;
import com.clx.ezgo.entity.DriverInfo;
import com.clx.ezgo.entity.Report;
import com.clx.ezgo.service.AdminService;
import com.clx.ezgo.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j(topic = "admin")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;


    @RequestMapping("/verifyAdmin")
    public void verifyAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8"); //设置编码

        String openid=request.getParameter("openID");
        String res=adminService.verifyAdmin(openid);


        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

       log.info("verifyAdmin"+result.toJSONString());
    }

    @RequestMapping("/getDriverRequests")
    public void getDriverRequests(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");


        List<DriverInfo> driverRequests=adminService.getDriverRequests();
        JSONObject result=new JSONObject();
        result.put("driverRequests",driverRequests);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("driverRequests");
    }

    @RequestMapping("/passRequest")
    public void passRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String driverID=request.getParameter("driverID");
        String res=adminService.passRequest(driverID);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("driverRequests");
    }

    @RequestMapping("/rejectRequest")
    public void rejectRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String driverID=request.getParameter("driverID");
        String res=adminService.rejectRequest(driverID);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("driverRequests");
    }

    @RequestMapping("/forbidUser")
    public void forbidUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openID=request.getParameter("openID");
        String res=adminService.forbidUser(openID);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("forbidUser");
    }

    @RequestMapping("/activeUser")
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openID=request.getParameter("openID");
        String res=adminService.activeUser(openID);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("activeUser");
    }

    @RequestMapping("/getReportByUser")
    public void getReportByUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openID=request.getParameter("openID");
        List<Report> reportList=adminService.getReportByUser(openID);
        JSONObject result=new JSONObject();
        result.put("reportList",reportList);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("reportList");
    }
}
