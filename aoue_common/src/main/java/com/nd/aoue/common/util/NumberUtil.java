package com.nd.aoue.common.util;

import java.text.DecimalFormat;

/**
 * 数字格式化工具类
 */
public class NumberUtil {
    /**
     *
     * @param num
     * @param length
     * @return
     */
    public static String format(int num, int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i ++){
            sb.append("0");
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        return df.format(num);
    }


    public static void main(String[] args) {
        System.out.println(format(1231, 4));
    }
}
