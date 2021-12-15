package com.clx.ezgo.controller;

import com.alibaba.fastjson.JSONObject;
import com.clx.ezgo.entity.DriverInfo;
import com.clx.ezgo.service.DriverService;
import com.clx.ezgo.utils.UploadUtils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Slf4j(topic = "dirver")
@RestController
@RequestMapping("/driver")
public class DriverController {
    private static final Logger logger= LoggerFactory.getLogger(DriverController.class);


    @Autowired
    DriverService driverService;

    @RequestMapping("/addDriver")
    public void addDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openid=request.getParameter("openID");
        String model=request.getParameter("model");
        String plate=request.getParameter("plateNumber");
        String color=request.getParameter("color");
        String res=driverService.addDriver(openid,model,plate,color);

        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("addDriver");
    }

    @RequestMapping("/uploadPicture")
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openid=request.getParameter("openID");
        String category=request.getParameter("cat");
        String filePath=UploadUtils.uploadDriverImg(openid,category,request);
        driverService.uploadImg(openid,category,filePath);

        log.info(filePath);
    }

    @RequestMapping("/getDriverInfoByID")
    public void getDriverInfoByID(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openid=request.getParameter("openID");
        DriverInfo driverInfo=driverService.getDriverInfoByID(openid);
        JSONObject result=new JSONObject();
        result.put("driverInfo",driverInfo);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("driverInfo");
    }
}
