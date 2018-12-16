package com.example.demotool.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
//        for (int i = 250000000; i < 270000000; i += 1000000) {
//            System.out.println("DELETE FROM tbCardTask WHERE lId > " + i + " and lId <= " + (i + 1000000) + " and nType = 1;");
//        }
        try {
            Date d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-10-30 00:00:00");
            Date d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-11-28 00:00:00");//定义结束日期
            Calendar dd = Calendar.getInstance();//定义日期实例
            dd.setTime(d1);//设置日期起始时间
            while (dd.getTime().before(d2)) {//判断是否到结束日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String str = sdf.format(dd.getTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date beginDatetime = simpleDateFormat.parse(str+" 00:00:00");
                Date endDateTime = simpleDateFormat.parse(str+" 23:59:59");
                System.out.println(simpleDateFormat.format(beginDatetime) +"       "+ simpleDateFormat.format(endDateTime));//输出日期结果
                dd.add(Calendar.DATE, 1);//进行当前日期月份加1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
