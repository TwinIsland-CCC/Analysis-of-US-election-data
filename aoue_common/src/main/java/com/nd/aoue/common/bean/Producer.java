package com.nd.aoue.common.bean;

import java.io.Closeable;
import java.io.IOException;

//生产者
public interface Producer extends Closeable {
    //数据输入
    void setIn(DataIn in);
    //数据输出
    void setOut(DataOut out);
    //生产数据
    void produce() throws IOException;
}
