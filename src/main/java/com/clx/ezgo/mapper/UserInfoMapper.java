package com.clx.ezgo.mapper;

import com.clx.ezgo.entity.UserInfo;
import com.clx.ezgo.entity.UserTripOverall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    public UserInfo getUserInfoByID(String id);
    public void  addUserInfo(UserInfo userInfo);
    public void changeCellphone(String id,String cellphone);
    public void changeNickname(String id,String nickname);
    public List<UserInfo> getAllUserInfo();
    public void forbidUser(String id);
    public void activeUser(String id);
    public UserTripOverall getUserTripOverall(String id);
}
