package com.nd.aoue.consumer.bean;

import com.nd.aoue.common.bean.Consumer;
import com.nd.aoue.common.constant.Names;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

//消费通话记录数据类
public class DataConsumer implements Consumer {
    /**
     * 消费数据
     * @throws IOException
     */
    @Override
    public void consume() throws IOException {
        //创建配置对象
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
        //获取flume采集的数据
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //关注主题
        consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));

        //实现HBase存储
        HBaseDao hBaseDao = new HBaseDao();
        //initial
        hBaseDao.init();
        int j = 0;
        while (true){
            //System.out.println("receiving……");
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            Integer i = 0;
            for (ConsumerRecord<String, String> r: records) {
                System.out.println(r.value());
                //存储数据到HBase中
                hBaseDao.insertData(r.value(),i);
                i++;
            }
            //System.out.println(++j * 100 + " complete");
            //System.out.println("next...");
        }


/*
        // 文件读入（调试用）
        String filePath = "D:\\hadoop\\Analysis-of-US-election-data\\data\\data_out1.csv";
        File file=new File(filePath);
        InputStreamReader in_stream = new InputStreamReader(new FileInputStream(file));
        BufferedReader in = new BufferedReader(in_stream);
        String s;
        Integer i = 0;
        while ((s=in.readLine())!=null) {
            hBaseDao.insertData(s,i);
            i++;
        }
        System.out.println("complete");
 */
    }



    /**
     * 释放资源
     * @throws IOException
     */
    @Override
    public void close() throws IOException {

    }
}
