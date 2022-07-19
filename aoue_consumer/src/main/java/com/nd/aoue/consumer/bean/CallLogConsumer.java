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
public class CallLogConsumer implements Consumer {
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
        //KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //关注主题
        //consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));

        //实现HBase存储
        HBaseDao hBaseDao = new HBaseDao();
        //initial
        hBaseDao.init();

        //消费数据
        String filePath = "D:\\hadoop\\Analysis-of-US-election-data\\data\\data_out1.csv";//data_out1是万条数据
        File file=new File(filePath);
        InputStreamReader in_stream = new    InputStreamReader(new FileInputStream(file));
        BufferedReader in = new BufferedReader(in_stream);
        String s;
        while ((s=in.readLine())!=null ) {
            //System.out.println(s);
            hBaseDao.insertData(s);
        }

/*
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> r: records) {
                System.out.println(r.value());
                //存储数据到HBase中
                hBaseDao.insertData(r.value());
            }
        }

*/
        System.out.println("Complete");
        //while(true) {
            //ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            //for (ConsumerRecord<String, String> record : records) {
            //System.out.println(record.value());
            //存储数据
            //hBaseDao.insertData(record.value());
            //}
        //}        //文件读入（调试用）
        while(true){
            String filePath = "D:\\JavaRepos\\Maven\\Analysis-of-US-election-data\\data\\data_out.csv";
            File file=new File(filePath);
            InputStreamReader in_stream = new InputStreamReader(new FileInputStream(file));
            BufferedReader in = new BufferedReader(in_stream);
            String s;
            while ((s=in.readLine())!=null) {
                System.out.println(s);
                hBaseDao.insertData(s);
            }
        }
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @Override
    public void close() throws IOException {

    }
}
