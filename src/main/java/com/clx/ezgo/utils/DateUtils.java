package com.clx.ezgo.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j(topic = "DateUtil")
public class DateUtils {

    public static Timestamp setDateInterval(String date,String mode){
        //2021-04-04 01:12:53
        //2021-04-04 00:00:00
        String dateStr=date.split(" ")[0];

        if(mode=="start")
            return Timestamp.valueOf(dateStr+" 00:00:00");
        else
            return Timestamp.valueOf(dateStr+" 23:59:59");
    }
    public static String getTokenTimeStr(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        return sdf.format(date);
    }
    public static void main(String[] args) {

        BigDecimal num1=new BigDecimal("3.00");
        BigDecimal num2=new BigDecimal("4.50");
        System.out.println((num1.multiply(new BigDecimal(2)).add(num2)).divide(new BigDecimal(3)));
    }
}
