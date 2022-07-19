package com.nd.auoe.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @ClassName:WriteSql
 * @Description:TODO
 * @Author:huge823865619
 * @Date:2022/7/1 7:06
 * @Version: 1.0
 */
public class ContactWriteSql {
    //写一个mapper
    public static class ContactSqlMapper extends Mapper<LongWritable,Text, Text,Contact>{
        private Text k=new Text();
        private Contact v=new Contact();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //获取一行数据
            String line=new String(value.getBytes(),0,value.getLength(),"UTF-8");
            //分割
            StringTokenizer sti=new StringTokenizer(line);
            v.setTell(sti.nextToken());
            v.setName(sti.nextToken());
            k.set(v.getTell());
            //输出
            context.write(k,v);
        }
    }
    //写一个Reducer
    public static class ContactSqlReducer extends Reducer<Text,Contact,Contact,NullWritable>{
        @Override
        protected void reduce(Text key, Iterable<Contact> values, Context context) throws IOException, InterruptedException {
            Contact bean = values.iterator().next();
            Contact k=new Contact(bean.getTell(),bean.getName());
            context.write(k,NullWritable.get());
        }
    }
    //Driver阶段
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        BasicConfigurator.configure();//使用缺省的log4j
        //创建配置对象
        Configuration conf=new Configuration();
        DBConfiguration.configureDB(conf,"com.mysql.jdbc.Driver",
                "jdbc:mysql://hadoop101:3306/ct?characterEncoding=utf-8",
                "root","root");
        //获取job对象
        Job job = Job.getInstance(conf);
        //设置jar位置
        job.setJarByClass(ContactWriteSql.class);
        //关联mapper和reducer
        job.setMapperClass(ContactSqlMapper.class);
        job.setReducerClass(ContactSqlReducer.class);
        //设置mapper输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Contact.class);
        //设置最终输出类型
        job.setOutputKeyClass(Contact.class);
        job.setOutputValueClass(NullWritable.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path("E:\\南开大学实训\\2.资料\\project\\电信客服\\辅助资料\\contact.log"));
        String[] fields={"tell","name"};
        DBOutputFormat.setOutput(job,"tb_user",fields);
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}

