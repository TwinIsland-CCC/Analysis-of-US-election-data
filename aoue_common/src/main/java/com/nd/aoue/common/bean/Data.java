package com.nd.aoue.common.bean;

//data
public abstract class Data implements Val {
    public String context;
    @Override
    public void setValue(Object val) {
        context = (String) val;
    }

    @Override
    public Object getValue() {
        return context;
    }
}
