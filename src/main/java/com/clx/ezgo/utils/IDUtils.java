package com.clx.ezgo.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.IdCardVerificationRequest;
import com.tencentcloudapi.faceid.v20180301.models.IdCardVerificationResponse;

public class IDUtils {

    public static String verifyIDcard(String name,String IDCard){
        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "", clientProfile);

            IdCardVerificationRequest req = new IdCardVerificationRequest();
            req.setIdCard(IDCard);
            req.setName(name);

            String resp = client.IdCardVerification(req).getResult();

            return resp;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return "erro!";
    }
}
