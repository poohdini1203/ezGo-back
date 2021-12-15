package com.clx.ezgo.controller;

import com.alibaba.fastjson.JSONObject;
import com.clx.ezgo.entity.DriverTrip;
import com.clx.ezgo.entity.PassengerTrip;
import com.clx.ezgo.entity.RatingOverall;
import com.clx.ezgo.entity.Report;
import com.clx.ezgo.mapper.DriverTripMapper;
import com.clx.ezgo.service.DriverService;
import com.clx.ezgo.service.DriverTripService;
import com.clx.ezgo.service.PassengerTripService;
import com.clx.ezgo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@Slf4j(topic = "driverTrip")
@RestController
@RequestMapping("/driverTrip")
public class DriverTripController {

    @Autowired
    DriverTripService driverTripService;
    @Autowired
    DriverService driverService;
    @RequestMapping("/add")
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openid=request.getParameter("openID");
        String departure=request.getParameter("departure");
        String destination=request.getParameter("destination");
        String departureCoordinate=request.getParameter("departureCoordinate");
        String destinationCoordinate=request.getParameter("destinationCoordinate");
        int seatCapacity=Integer.valueOf(request.getParameter("howManyPeople"));
        int distance=Integer.valueOf(request.getParameter("distance"));
        String time=request.getParameter("departTime");
        Timestamp departTime= Timestamp.valueOf(time);
        String postscript=request.getParameter("postscript");
        DriverTrip driverTrip =new DriverTrip(openid,departure,destination,departureCoordinate,destinationCoordinate,
                seatCapacity,departTime,postscript,distance);
        driverTripService.addTrip(driverTrip);
    }
    @RequestMapping("/getTripByTripID")
    public void getTripByTripID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int tripID=Integer.valueOf(request.getParameter("tripID"));
        DriverTrip driverTrip=driverTripService.getTripByTripID(tripID);
        RatingOverall ratingOverall=driverService.getRatingOverall(driverTrip.getDriverID());
        JSONObject result=new JSONObject();
        result.put("driverTrip",driverTrip);
        result.put("ratingOverall",ratingOverall);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("driverTrip");
        log.info(result.toJSONString());
    }

    @RequestMapping("/getCurrentTripsByDriverID")
    public void getCurrentTripsByDriverID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String driverID=request.getParameter("driverID");
        log.info(driverID);
        List<DriverTrip> driverTripList=driverTripService.getCurrentTripsByDriverID(driverID);
        JSONObject result=new JSONObject();
        result.put("driverTrips",driverTripList);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());


    }

    @RequestMapping("/getHistoryByDriverID")
    public void getHistoryByDriverID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String driverID=request.getParameter("driverID");
        log.info(driverID);
        List<DriverTrip> driverTripList=driverTripService.getHistoryByDriverID(driverID);
        JSONObject result=new JSONObject();
        result.put("driverTrips",driverTripList);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
    }

    @RequestMapping("/getAllTripsByDriverID")
    public void getAllTripsByDriverID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String driverID=request.getParameter("driverID");
        log.info(driverID);
        List<DriverTrip> driverTripList=driverTripService.getAllTripsByDriverID(driverID);
        JSONObject result=new JSONObject();
        result.put("driverTrips",driverTripList);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
    }
    @RequestMapping("/getTripsByCondition")
    public void getTripsByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        List<DriverTrip> driverTrips=driverTripService.getTripsByCondition(departure,destination,timeStart,timeEnd,howManyPeople);

        JSONObject result=new JSONObject();
        result.put("driverTrips",driverTrips);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("driverTripsCondition");
        log.info(result.toJSONString());
    }

    @RequestMapping("/tripCancel")
    public void tripCancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int tripID=Integer.valueOf(request.getParameter("tripID"));
        String res=driverTripService.cancelTrip(tripID);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("tripCancel");
        log.info(result.toJSONString());
    }

    @RequestMapping("/tripFinish")
    public void tripFinish(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int tripID=Integer.valueOf(request.getParameter("tripID"));
        String res=driverTripService.finishTrip(tripID);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("tripFinish");
        log.info(result.toJSONString());
    }

    @RequestMapping("/reportPassenger")
    public void reportPassenger(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String receiverID=request.getParameter("receiverID");
        int receiverTripID=Integer.valueOf(request.getParameter("receiverTripID"));
        String reporterID=request.getParameter("reporterID");
        int reporterTripID=Integer.valueOf(request.getParameter("reporterTripID"));
        String content=request.getParameter("content");
        Report report=new Report(receiverID,receiverTripID,reporterID,reporterTripID,1,content);
        String res=driverTripService.reportPassenger(report);

        JSONObject result=new JSONObject();
        result.put("result",res);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        log.info("reportPassenger");
        log.info(result.toJSONString());
    }
}
