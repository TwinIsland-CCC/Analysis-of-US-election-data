package com.nd.aoue.common.bean;

import java.io.Closeable;

//数据输出
public interface DataOut extends Closeable {
    //output path
    void setPath(String path);
    //写出数据
    void write(Object obj) throws Exception;
    void write(String obj) throws Exception;
}
