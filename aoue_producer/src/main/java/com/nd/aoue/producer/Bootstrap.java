package com.nd.aoue.producer;


import com.nd.aoue.common.bean.Producer;
import com.nd.aoue.producer.IO.LocalFileDataIn;
import com.nd.aoue.producer.IO.LocalFileDataOut;
import com.nd.aoue.producer.bean.LocalFileProducer;

import java.io.IOException;

//启动类
public class Bootstrap {
    public static void main(String[] args) throws IOException {

        if(args.length <= 0){
            System.out.println("系统参数错误，请按照格式传参：" +
                    "java -jar producer.jar data_****.csv data_out.csv");
            System.exit(1);
        }


        // 构建生产者对象
        Producer producer = new LocalFileProducer();
        //人工
        //producer.setIn(new LocalFileDataIn("D:\\hadoop\\nku\\新建文件夹\\data1.csv"));
        //producer.setOut(new LocalFileDataOut("D:\\hadoop\\Analysis-of-US-election-data\\data\\data_out1.csv"));

        //集群
        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));

        // 生产数据
        producer.produce();
        // 释放资源
        producer.close();

    }
}
