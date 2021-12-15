package com.clx.ezgo.controller;

import com.alibaba.fastjson.JSONObject;
import com.clx.ezgo.entity.PassengerTrip;
import com.clx.ezgo.entity.RatingDetail;
import com.clx.ezgo.entity.Report;
import com.clx.ezgo.service.PassengerTripService;
import com.clx.ezgo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/passengerTrip")
public class PassengerTripController {

    @Autowired
    PassengerTripService passengerTripService;


    @RequestMapping("/add")
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openid=request.getParameter("openID");
        String departure=request.getParameter("departure");
        String destination=request.getParameter("destination");
        String departureCoordinate=request.getParameter("departureCoordinate");
        String destinationCoordinate=request.getParameter("destinationCoordinate");
        int howManyPeople=Integer.valueOf(request.getParameter("howManyPeople"));
        int distance=Integer.valueOf(request.getParameter("distance"));
        String time=request.getParameter("departTime");
        Timestamp departTime= Timestamp.valueOf(time);

        String postscript=request.getParameter("postscript");
        PassengerTrip passengerTrip =new PassengerTrip(openid,departure,destination,departureCoordinate,destinationCoordinate,
                                                            howManyPeople,departTime,postscript,distance);
        passengerTripService.addTrip(passengerTrip);
    }

    @RequestMapping("/getHistoryByPassengerID")
    public void getHistoryByPassengerID(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openID=request.getParameter("openID");
        List<PassengerTrip> passengerTripList=passengerTripService.getHistoryByPassengerID(openID);
        JSONObject result=new JSONObject();
        result.put("passengerTrips",passengerTripList);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("historyPassengerTrips");
        log.info(result.toJSONString());
    }

    @RequestMapping("/getAllTripsByPassengerID")
    public void getAllTripsByPassengerID(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openID=request.getParameter("openID");
        List<PassengerTrip> passengerTripList=passengerTripService.getAllTripsByPassengerID(openID);
        JSONObject result=new JSONObject();
        result.put("passengerTrips",passengerTripList);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("allPassengerTrips");
        log.info(result.toJSONString());
    }

    @RequestMapping("/getTripsByCondition")
    public void getTripsByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

//String departure, String destination, Timestamp timeStart,Timestamp timeEnd,int howManyPeople

        String departure=request.getParameter("departure");
        String destination=request.getParameter("destination");
        String date=request.getParameter("date");
        String howManyPeople=request.getParameter("howManyPeople");
        //默认时间范围
        Timestamp timeStart=Timestamp.valueOf("2001-04-05 02:51:00");
        Timestamp timeEnd=Timestamp.valueOf("2099-04-05 02:51:00");

        log.info("departure:"+departure);
        log.info("destination:"+destination);
        log.info("date:"+date);
        log.info("howManyPeople:"+howManyPeople);

        //如果指定了日期
        if(date!=null&&date.length()!=0) {
            timeStart = DateUtils.setDateInterval(date, "start");
            timeEnd = DateUtils.setDateInterval(date, "end");
        }
        List<PassengerTrip> passengerTrips=passengerTripService.getTripsByCondition(departure,destination,timeStart,timeEnd,howManyPeople);

        JSONObject result=new JSONObject();
        result.put("passengerTrips",passengerTrips);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("passengerTripsCondition");
        log.info(result.toJSONString());
    }

    @RequestMapping("/getCurrentTripsByPassengerID")
    public void getCurrentTripsByPassengerID(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openID=request.getParameter("openID");
        List<PassengerTrip> passengerTripList=passengerTripService.getCurrentTripsByPassengerID(openID);
        JSONObject result=new JSONObject();
        result.put("passengerTrips",passengerTripList);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("CurrentPassengerTrips");
        log.info(openID);
        log.info(result.toJSONString());
    }

    @RequestMapping("/getTripByTripID")
    public void getTripByTripID(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int tripID=Integer.valueOf(request.getParameter("tripID"));
        PassengerTrip passengerTrip=passengerTripService.getTripByTripID(tripID);

        JSONObject result=new JSONObject();
        result.put("passengerTrip",passengerTrip);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("passengerTrip");
        log.info(result.toJSONString());
    }

    @RequestMapping("/tripCancel")
    public void tripCancel(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int tripID=Integer.valueOf(request.getParameter("tripID"));
        String res=passengerTripService.tripCancel(tripID);

        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("cancel");
        log.info(result.toJSONString());
    }

    @RequestMapping("/confirmPickedUp")
    public void confirmPickedUp(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int tripID=Integer.valueOf(request.getParameter("tripID"));
        String res=passengerTripService.confirmPickedUp(tripID);

        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("confirmPickedUp");
        log.info(result.toJSONString());
    }

    @RequestMapping("/confirmFinish")
    public void confirmFinish(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int tripID=Integer.valueOf(request.getParameter("tripID"));
        String res=passengerTripService.confirmFinish(tripID);

        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("confirmFinish");
        log.info(result.toJSONString());
    }

    @RequestMapping("/reportDriver")
    public void reportDriver(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String receiverID=request.getParameter("receiverID");
        int receiverTripID=Integer.valueOf(request.getParameter("receiverTripID"));
        String reporterID=request.getParameter("reporterID");
        int reporterTripID=Integer.valueOf(request.getParameter("reporterTripID"));
        String content=request.getParameter("content");
        Report report=new Report(receiverID,receiverTripID,reporterID,reporterTripID,0,content);

        String res=passengerTripService.reportDriver(report);
        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("reportDriver");
        log.info(result.toJSONString());
    }


    private String driverID;
    private int driverTripID;
    private String passengerID;
    private int passengerTripID;
    private BigDecimal rating;
    @RequestMapping("/rateDriver")
    public void rateDriver(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String driverID=request.getParameter("driverID");
        int driverTripID=Integer.valueOf(request.getParameter("driverTripID"));
        String passengerID=request.getParameter("passengerID");
        int passengerTripID=Integer.valueOf(request.getParameter("passengerTripID"));
        String ratingStr=request.getParameter("rating");
        BigDecimal rating=new BigDecimal(ratingStr);
        RatingDetail ratingDetail=new RatingDetail(driverID,driverTripID,passengerID,passengerTripID,rating);

        String res=passengerTripService.rateDriver(ratingDetail);
        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("rateDriver");
        log.info(result.toJSONString());
    }
}
