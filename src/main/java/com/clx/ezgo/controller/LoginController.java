package com.clx.ezgo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.clx.ezgo.entity.UserToken;
import com.clx.ezgo.utils.DateUtils;
import com.clx.ezgo.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Slf4j(topic = "login")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("/getUserSecret")
   public void getUserSecret(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String code=request.getParameter("code");
        log.info("code" + code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=wxe986290470a50e70";//自己的appid
        url += "&secret=33e212107a66ff87a3fa0ccb67ad5dff";//自己的appSecret
        url += "&js_code=" + code;
        url += "&grant_type=authorization_code";
        url += "&connect_redirect=1";
        String res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);    //GET方式
        CloseableHttpResponse httpResponse = null;
        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()          // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(5000)                    // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(5000)             // socket读写超时时间(单位毫秒)
                .setSocketTimeout(5000)                    // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(false).build();           // 将上面的配置信息 运用到这个Get请求里
        httpget.setConfig(requestConfig);                         // 由客户端执行(发送)Get请求
        httpResponse = httpClient.execute(httpget);                   // 从响应模型中获取响应实体
        HttpEntity responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
            res = EntityUtils.toString(responseEntity);
            log.info("响应内容为:" + res);
        }
        // 释放资源
        if (httpClient != null) {
            httpClient.close();
        }
        if (httpResponse != null) {
            httpResponse.close();
        }
        JSONObject jo = JSON.parseObject(res);
        String openid = jo.getString("openid");
        String sessonKey=jo.getString("session_key");
        String token=openid+ DateUtils.getTokenTimeStr(new Date())+sessonKey;
        String key="userToken_"+openid;
        log.info("开始操作redis");
        redisUtils.set(key,token,60*60*2);
        log.info("set完毕");

        JSONObject result=new JSONObject();
        result.put("openid",openid);
        result.put("token",token);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(result.toJSONString());
        log.info("已经生成token");
   }
}
