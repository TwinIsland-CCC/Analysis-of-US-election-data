package com.nd.aoue.common.bean;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

//数据输入
public interface DataIn extends Closeable {
    //input path
    void setPath(String path);
    // read data
    Object read() throws IOException;
    // return set
    <T extends Data> List<T> read(Class<T> tClass) throws IOException;

    <T extends Data> List<T>  readPart(Class<T> tClass) throws IOException;
}
