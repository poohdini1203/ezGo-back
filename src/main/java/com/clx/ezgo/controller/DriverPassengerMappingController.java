package com.clx.ezgo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.clx.ezgo.entity.*;
import com.clx.ezgo.service.DriverPassengerMappingService;
import com.clx.ezgo.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@Slf4j(topic = "mapping")
@RestController
@RequestMapping("/mapping")
public class DriverPassengerMappingController {
    @Autowired
    private  DriverPassengerMappingService mappingService;
    @Autowired
    DriverService driverService;
    @RequestMapping("/driverInvitePassenger")
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int driverTripID=Integer.valueOf(request.getParameter("driverTripID"));
        int passengerTripID=Integer.valueOf(request.getParameter("passengerTripID"));
        DriverPassengerMapping mapping=new DriverPassengerMapping(driverTripID,passengerTripID);
        String res=mappingService.driverInvitePassenger(mapping);
        log.info(res);

        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("driverInvitePassenger");
        log.info(result.toJSONString());
    }
    @RequestMapping("/driverTakeOrder")
    public void driverTakeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String driverID=request.getParameter("driverID");
        String passengerTrip=request.getParameter("passengerTrip");
        PassengerTrip pt= JSON.parseObject(passengerTrip,PassengerTrip.class);

        String res=mappingService.driverTakeOrder(driverID,pt);
        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("driverTakeOrder");
    }
    @RequestMapping("/getPassengersOfDriverTrip")
    public void getPassengersOfDriverTrip(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int driverTripID=Integer.valueOf(request.getParameter("driverTripID"));
        List<PassengerTrip> passengerTrips=mappingService.getPassengersOfDriverTrip(driverTripID);
        JSONObject result=new JSONObject();
        result.put("passengerTrips",passengerTrips);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("Passengers");
        log.info(result.toJSONString());
    }

    @RequestMapping("/passengerInviteDriver")
    public void passengerInviteDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int driverTripID=Integer.valueOf(request.getParameter("driverTripID"));
        int passengerTripID=Integer.valueOf(request.getParameter("passengerTripID"));
        DriverPassengerMapping mapping=new DriverPassengerMapping(driverTripID,passengerTripID);
        String res=mappingService.passengerInviteDriver(mapping);

        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("passengerInvite");
        log.info(result.toJSONString());
    }

    @RequestMapping("/passengerJoinDrvier")
    public void passengerJoinDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String passengerID=request.getParameter("passengerID");
        String driverTrip=request.getParameter("driverTrip");
        int howManyPeople=Integer.valueOf(request.getParameter("howManyPeople"));
        String postscript=request.getParameter("postscript");
        DriverTrip dt= JSON.parseObject(driverTrip,DriverTrip.class);

        String res=mappingService.passengerJoinDrvier(passengerID,dt,howManyPeople,postscript);
        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("passengerJoin");
        log.info(result.toJSONString());
    }
    @RequestMapping("/getDriverTrip")
    public void getDriverTrip(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int passengerTripID=Integer.valueOf(request.getParameter("passengerTripID"));
        log.info(String.valueOf(passengerTripID));
        DriverTrip driverTrip=mappingService.getDriverTrip(passengerTripID);
        RatingOverall ratingOverall=driverService.getRatingOverall(driverTrip.getDriverID());
        JSONObject result=new JSONObject();
        result.put("driverTrip",driverTrip);
        result.put("ratingOverall",ratingOverall);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("getDriverTrip");
        log.info(result.toJSONString());
    }
    @RequestMapping("/getPassengerInvitations")
    public void getPassengerInvitations(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int driverTripID=Integer.valueOf(request.getParameter("driverTripID"));
        List<PassengerTrip> invitations=mappingService.getPassengerInvitations(driverTripID);

        log.info(request.getParameter("driverTripID"));
        JSONObject result=new JSONObject();
        result.put("invitations",invitations);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("invitations");
        log.info(result.toJSONString());
    }

    @RequestMapping("/driverAcceptInvitation")
    public void driverAcceptInvitation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int driverTripID=Integer.valueOf(request.getParameter("driverTripID"));
        int passengerTripID=Integer.valueOf(request.getParameter("passengerTripID"));
        DriverPassengerMapping mapping=new DriverPassengerMapping(driverTripID,passengerTripID);
        String res=mappingService.driverAcceptInvitation(mapping);

        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("accept");
        log.info(result.toJSONString());
    }

    @RequestMapping("/driverRefuseInvitation")
    public void driverRefuseInvitation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int driverTripID=Integer.valueOf(request.getParameter("driverTripID"));
        int passengerTripID=Integer.valueOf(request.getParameter("passengerTripID"));
        DriverPassengerMapping mapping=new DriverPassengerMapping(driverTripID,passengerTripID);
        String res=mappingService.driverRefuseInvitation(mapping);

        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("refuse");
        log.info(result.toJSONString());
    }

    @RequestMapping("/driverCancelPassengerTrip")
    public void driverCancelPassengerTrip(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int driverTripID=Integer.valueOf(request.getParameter("driverTripID"));
        int passengerTripID=Integer.valueOf(request.getParameter("passengerTripID"));
        log.info("{}",driverTripID);
        log.info("{}",passengerTripID);
        DriverPassengerMapping mapping=new DriverPassengerMapping(driverTripID,passengerTripID);
        String res=mappingService.driverCancelPassengerTrip(mapping);

        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("refuse");
        log.info(result.toJSONString());
    }
    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response){
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                mappingService.getDriverTrip(40);
            }
        },"t1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                mappingService.getDriverTrip(40);
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
