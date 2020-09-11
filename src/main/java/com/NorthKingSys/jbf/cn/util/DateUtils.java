package com.NorthKingSys.jbf.cn.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {

    // 将String转换位Date
    public Date parse(String date)  {

        if(date == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date newDate = new Date();
        try {
            newDate = simpleDateFormat.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return newDate;
    }

    public String format(Date date)  {
        if(date == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }


}
