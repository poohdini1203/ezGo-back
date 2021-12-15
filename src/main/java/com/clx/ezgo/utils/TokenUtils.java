package com.clx.ezgo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j(topic = "TokenUtil")
@Component
public class TokenUtils {
    @Autowired
    RedisUtils redisUtils;
    public  boolean isTokenValid(String openid,String token){
        if(openid==null||openid.length()==0){
            return false;
        }
        if(token==null||token.length()==0) {
            return false;
        }
        String key="userToken_"+openid;
        //当前用户没有token
        if(redisUtils.get(key)==null){
            log.info("没有值");
            return false;
        }
        //token不对
        log.info(redisUtils.get(key).toString());
        if(!token.equals(redisUtils.get(key).toString())){
            log.info("token不对");
            return false;
        }
        log.info("有效");
        return true;
    }
}
