package com.nd.aoue.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//生成日期格式用的工具类
public class DateUtil {
    public static Map<String, String> DateMap = null;

    /**
     * 初始化DateMap
     */
    private static void dateMapInit(){
        DateMap = new HashMap<>();
        DateMap.put("JAN", "01");DateMap.put("FEB", "02");DateMap.put("MAR", "03");
        DateMap.put("APR", "04");DateMap.put("MAY", "05");DateMap.put("JUN", "06");
        DateMap.put("JUL", "07");DateMap.put("AUG", "08");DateMap.put("SEP", "09");
        DateMap.put("OCT", "10");DateMap.put("NOV", "11");DateMap.put("DEC", "12");
    }


    /**
     * 将日期字符串解析为日期对象
     * @param dateString 日期字符串
     * @param format 格式
     * @return 解析后对象
     */
    public static Date parse(String dateString, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将日期按格式转化为字符串
     * @param date 日期
     * @param format 格式
     * @return 转化后的字符串
     */
    public static String format(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将源数据中的日期字符串转化为yyMMdd类型
     * @param input 源数据中的字符串
     * @return yyMMdd型字符串
     */
    public static String parse(String input){
        if(DateMap == null) dateMapInit();
        String[] split = input.split("-");
        //yyMMdd
        return split[2]+DateMap.get(split[1])+split[0];
    }
}
