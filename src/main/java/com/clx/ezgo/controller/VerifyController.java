package com.clx.ezgo.controller;

import com.alibaba.fastjson.JSONObject;
import com.clx.ezgo.utils.IDUtils;
import com.clx.ezgo.utils.SmsUtils;

import com.clx.ezgo.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j(topic = "verify")
@RestController
@RequestMapping("/verify")
public class VerifyController {

    @Autowired
    TokenUtils tokenUtils;
    @RequestMapping("/cellphone")
    public void verifyCellphone(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JSONObject result=new JSONObject();

        //获取openid和token
        String openid=request.getHeader("openID");
        String token=request.getHeader("token");
        if(!tokenUtils.isTokenValid(openid,token)){
            //token错误
            result.put("code","1");
        }
        else {
            String templateCode=request.getParameter("templateCode");
            String cellphone=request.getParameter("cellphone");
            String varificationCode= SmsUtils.makeSixBitCode();
            SmsUtils.sendShortMessage(templateCode,cellphone,varificationCode);
            result.put("code","0");
            result.put("varificationCode",varificationCode);
        }

        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info(result.toJSONString());
    }
    @RequestMapping("/IDCard")
    public void verifyIDCard(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JSONObject result=new JSONObject();

        //获取openid和token
        String openid=request.getHeader("openID");
        String token=request.getHeader("token");
        if(!tokenUtils.isTokenValid(openid,token)){
            //token错误
            result.put("code","1");
        }
        else {
            String name=request.getParameter("name");
            String IDCard=request.getParameter("IDCard");
            String IDResult= IDUtils.verifyIDcard(name,IDCard);
            result.put("code","0");
            result.put("IDResult",IDResult);
        }
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info(result.toJSONString());
//        data: {Result: "0", Description: "?????????", RequestId: "e2e450fa-9f59-461d-9cbf-e1bfb5fe5233"}
    }
}
