package com.nd.aoue.common.util;

import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    /**
     * CSV数据特定的split方法
     * @param line 输入的数据
     * @return 分割好的字符串数组
     */
    public static String[] split(String line) {
        List<String> list = new ArrayList<>();
        int start = 0;
        int end = -1;
        do {
            start = end + 1;
            if (line.charAt(start) == '\"') {
                end = line.indexOf("\"", start + 1) + 1;
            } else {
                end = line.indexOf(",", start);
            }
            if (end < 0) end = line.length();

            String p1 = line.substring(start, end);
            list.add(p1);
            //System.out.println(p1);
        } while (end < line.length() - 1);
        return list.toArray(new String[0]);
    }
}
