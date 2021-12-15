package com.clx.ezgo.service.impl;


import com.clx.ezgo.VO.UserInfoVO;
import com.clx.ezgo.entity.UserInfo;
import com.clx.ezgo.entity.UserTripOverall;
import com.clx.ezgo.mapper.UserInfoMapper;
import com.clx.ezgo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserInfoImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoByID(String id) {
        if(id!=null&&id.length()!=0) {
            UserInfo userInfo=userInfoMapper.getUserInfoByID(id);
            if(userInfo!=null){
                return userInfo;
            }
        }
        return null;
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfos=userInfoMapper.getAllUserInfo();
        return userInfos;
    }

    @Override
    public String addUserInfo(UserInfo userInfo) {
        String id=userInfo.getId();
        UserInfo check=userInfoMapper.getUserInfoByID(id);
        if(check==null){
            userInfoMapper.addUserInfo(userInfo);
            return "0";
        }
        //失败
        return "1";
    }

    @Override
    public String changeCellphone(String id, String cellphone) {
        UserInfo check=userInfoMapper.getUserInfoByID(id);
        if(check!=null){
            userInfoMapper.changeCellphone(id,cellphone);
            return "0";
        }
        return "1";
    }

    @Override
    public String changeNickname(String id, String nickname) {
        UserInfo check=userInfoMapper.getUserInfoByID(id);
        if(check!=null){
            userInfoMapper.changeNickname(id,nickname);
            return "0";
        }
        return "1";
    }

    @Override
    public UserTripOverall getOverall(String userID) {
        return userInfoMapper.getUserTripOverall(userID);
    }

    public static void main(String[] args) {

    }
}
