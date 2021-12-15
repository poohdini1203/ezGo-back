package com.clx.ezgo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "DistributedLock")
@Component
public class DistributedLock {
    private final long expireTime=50000;
    private final long timeout=10000;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean Lock(String lockId) {
        long start=System.currentTimeMillis();
        //成功获得就返回，否则循环尝试获取锁，超时就失败
        while (true){
            log.info("尝试获得锁");
            Boolean success = redisTemplate.opsForValue().setIfAbsent(lockId, "lock",
                    expireTime, TimeUnit.MILLISECONDS);
            if(success){
                log.info("获得锁");
                return true;
            }
            log.info("没有获得锁");
            long waitTime=System.currentTimeMillis()-start;
            if(waitTime>timeout){
                log.info("超时");
                return false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void releaseLock(String lockId) {
        redisTemplate.delete(lockId);
        log.info("释放锁");
    }
}
