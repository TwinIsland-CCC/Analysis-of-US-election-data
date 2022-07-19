package com.nd.auoe.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
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

/**
 * @ClassName:DateWriteSql
 * @Description:TODO
 * @Author:huge823865619
 * @Date:2022/7/10 21:42
 * @Version: 1.0
 */
public class DateWriteSql {
    //Mapper阶段
    public static class DateWriteSqlMapper extends Mapper<LongWritable, Text, IntWritable,DateSql>{
        private IntWritable k=new IntWritable();
        private DateSql v=new DateSql();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line=new String(value.getBytes(),0,value.getLength(),"UTF-8");
            String[] split = line.split(",", -1);
            v.setId(Integer.parseInt(split[0]));
            v.setYear(split[1]);
            v.setMonth(split[2]);
            v.setDay(split[3]);
            k.set(v.getId());
            context.write(k,v);
        }
    }
    //写reducer阶段
    public static class DateWriteSqlReducer extends Reducer<IntWritable,DateSql,DateSql, NullWritable>{
        @Override
        protected void reduce(IntWritable key, Iterable<DateSql> values, Context context) throws IOException, InterruptedException {
            DateSql bean = values.iterator().next();
            DateSql k=new DateSql(bean.getId(),bean.getYear(),bean.getMonth(),bean.getDay());
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
        job.setJarByClass(DateWriteSql.class);
        //关联mapper和reducer
        job.setMapperClass(DateWriteSqlMapper.class);
        job.setReducerClass(DateWriteSqlReducer.class);
        //设置mapper输出类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(DateSql.class);
        //设置最终输出类型
        job.setOutputKeyClass(DateSql.class);
        job.setOutputValueClass(NullWritable.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path("E:\\南开大学实训\\2.资料\\project\\电信客服\\辅助资料\\date.csv"));
        String[] fields={"id","year","month","day"};
        DBOutputFormat.setOutput(job,"tb_date",fields);
        //提交
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}

