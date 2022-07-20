package com.nd.aoue.consumer;

import com.nd.aoue.common.bean.Consumer;
import com.nd.aoue.consumer.bean.DataConsumer;

import java.io.IOException;


/**
 * 使用kafka消费者获取flume类型数据
 * 将数据存储到HBase中
 */
public class
BootStrap {
    public static void main(String[] args) throws IOException {
        //创建消费者
        Consumer consumer = new DataConsumer();
        //消费数据
        consumer.consume();
        //释放资源
        consumer.close();
    }
}
