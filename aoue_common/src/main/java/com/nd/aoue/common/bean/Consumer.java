package com.nd.aoue.common.bean;

import java.io.Closeable;
import java.io.IOException;

//消费者interface
public interface Consumer extends Closeable {
    // 消费数据
    void consume() throws IOException;


}
