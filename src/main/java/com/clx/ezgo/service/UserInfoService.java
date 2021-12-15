package com.clx.ezgo.service;


import com.clx.ezgo.VO.UserInfoVO;
import com.clx.ezgo.entity.UserInfo;
import com.clx.ezgo.entity.UserTripOverall;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserInfoService {
    public UserInfo getUserInfoByID(String id);
    public List<UserInfo> getAllUserInfo();
    public String addUserInfo(UserInfo userInfo);
    public String changeCellphone(String id,String cellphone);
    public String changeNickname(String id,String nickname);
    public UserTripOverall getOverall(String userID);
}
