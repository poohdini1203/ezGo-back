package com.clx.ezgo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clx.ezgo.VO.UserInfoVO;
import com.clx.ezgo.entity.UserInfo;
import com.clx.ezgo.entity.UserTripOverall;
import com.clx.ezgo.service.UserInfoService;
import com.clx.ezgo.utils.RedisUtils;
import com.clx.ezgo.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@Slf4j(topic = "userInfo")
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/addUserInfo")
    public void addUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String submit=request.getParameter("userInfo");
        System.out.println(submit);

        //解析JSON
        JSONObject json=JSONObject.parseObject(submit);
        UserInfo userInfo=new UserInfo();
        userInfo.setId(json.getString("openID"));
        userInfo.setName(json.getString("name"));
        userInfo.setAvatarUrl(json.getString("avatarUrl"));
        userInfo.setNickName(json.getString("nickName"));
        userInfo.setIDCard(json.getString("IDCard"));
        userInfo.setCellphone(json.getString("cellphone"));

        //返回结果
        String res=userInfoService.addUserInfo(userInfo);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
    }
    @RequestMapping("/getUserInfoByID")
    public void getUserInfoByID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JSONObject result=new JSONObject();

        String openid=request.getHeader("openID");
        String token=request.getHeader("token");
        log.info(openid);
        log.info(token);
        //token无效
        if(!tokenUtils.isTokenValid(openid,token)){
            result.put("code",1);
        }
        else {
            String id=request.getParameter("openID");
            UserInfo userInfo=userInfoService.getUserInfoByID(id);
            result.put("code",0);
            result.put("userInfo",userInfo);
        }
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info(result.toJSONString());
    }

    @RequestMapping("/getAllUserInfo")
    public void getAllUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JSONObject result=new JSONObject();

        List<UserInfo> userInfos=userInfoService.getAllUserInfo();
        result.put("userInfoList",userInfos);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        System.out.println(result.toJSONString());
    }

    @RequestMapping("/changeCellphone")
    public void changeCellphone(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String id=request.getParameter("openID");
        String phone=request.getParameter("cellphone");


        String res=userInfoService.changeCellphone(id,phone);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        System.out.println(phone+" "+result.toJSONString());
    }

    @RequestMapping("/changeNickname")
    public void changeNickname(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String id=request.getParameter("openID");
        String nickname=request.getParameter("nickName");

        String res=userInfoService.changeNickname(id,nickname);
        JSONObject result=new JSONObject();
        result.put("result",res);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

        System.out.println(id+" "+nickname+" "+result.toJSONString());
    }
    @RequestMapping("/getUserTripOverall")
    public void getUserTripOverall(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String id=request.getParameter("openID");
        UserTripOverall userTripOverall=userInfoService.getOverall(id);
        JSONObject result=new JSONObject();
        result.put("userTripOverall",userTripOverall);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());

    }
}
