package com.nd.aoue.common.constant;

import sun.security.jca.GetInstance;

import java.util.HashMap;

/**
 * 获取总统党派信息的工具类
 */
public class Parties {
    public static HashMap<String, String> parties = null;

    private static void init(){
        parties = new HashMap<>();
        parties.put("Bachmann, Michelle", "Republican");
        parties.put("Cain, Herman", "Republican");
        parties.put("Gingrich, Newt", "Republican");
        parties.put("Huntsman, Jon", "Republican");
        parties.put("Johnson, Gary Earl", "Republican");
        parties.put("McCotter, Thaddeus G", "Republican");
        parties.put("Obama, Barack", "Democrat");
        parties.put("Paul, Ron", "Republican");
        parties.put("Pawlenty, Timothy", "Republican");
        parties.put("Perry, Rick", "Republican");
        parties.put("Roemer, Charles E. 'Buddy' III", "Republican");
        parties.put("Romney, Mitt", "Republican");
        parties.put("Santorum, Rick", "Republican");
    }

    /**
     * 使用HashMap索引总统党派
     * @param key 总统名字
     * @return 总统党派
     */
    public static String getParty(String key) {
        if(parties == null) init();
        return parties.get(key);
    }

}
