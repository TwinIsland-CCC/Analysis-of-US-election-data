package com.nd.aoue.common.constant;

import com.nd.aoue.common.bean.Val;

/**
 * 枚举类型常量
 */

public enum Names implements Val {
    TOPIC("aoue"),  // 主题
    NAMESPACE("aoue"),//命名空间
    TABLE("aoue:data"),//数据
    CF_CAND("cand"),//候选人
    CF_INFO("info"),//列族
    ;


    private String name;

    Names(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object val) {
        this.name = (String) val;
    }

    @Override
    public String getValue() {
        return name;
    }
}
