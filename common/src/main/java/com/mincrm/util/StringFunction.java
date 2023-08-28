package com.mincrm.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by sunhh on 2019/7/25.
 */
public class StringFunction {


    /**
     * 时间计算  分钟
     * @param startDate
     * @param minute
     * @return
     */
    public static Date timeCalculation(Date startDate,int minute){
       // SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh24:mi");
        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE,-minute);
      //  System.out.println("date:"+formatter.format(cal.getTime()));
        return  cal.getTime();
    }

    /***
     * 时间转字符窜
     * @param d
     * @param format
     * @return
     */
    public static String dateToString(Date d,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str =  sdf.format(d);
        return str;
    }
    /***
     *
     * @param str 字符串转date
     * @return
     */
    public static Date stringToDate(String str){
        if(StringUtils.isEmpty(str))
            return null;
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        Date rdate = null;
        try {
            rdate = sDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rdate;
    }

    /**
     * 返回长度为【strLength】的随机数，
     * @param strLength 长度
     * @return 随机数
     */
    public static String getFixLengthString(int strLength) {
        StringBuffer sb  = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<strLength;i++){
            String d = String.valueOf(random.nextInt());
            sb.append(StringUtils.substring(d,d.length()-1));
        }
        return sb.toString();
    }

}
