package com.nd.aoue.analyzer.tools;

import com.nd.aoue.analyzer.io.SupportMySQLOutputFormat;
import com.nd.aoue.analyzer.mapper.SupportMapper;
import com.nd.aoue.analyzer.reducer.SupportReducer;
import com.nd.aoue.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;


/**
 * 分析数据的工具类
 */
public class SupportTool implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        //获取job
        Job job= Job.getInstance();
        //设置jar位置
        job.setJarByClass(SupportTool.class);
        //扫描主叫列族
        Scan scan=new Scan();
        //TODO 本组化
        scan.addFamily(Bytes.toBytes(Names.CF_CAND.getValue()));
        //设置mapper
        TableMapReduceUtil.initTableMapperJob(
                Names.TABLE.getValue(),
                scan,
                SupportMapper.class,
                Text.class,
                Text.class,
                job
        );
        //设置reducer
        job.setReducerClass(SupportReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //设置输出
        job.setOutputFormatClass(SupportMySQLOutputFormat.class);
        //提交
        boolean flag = job.waitForCompletion(true);
        if(flag){
            return JobStatus.State.SUCCEEDED.getValue();
        }else{
            return JobStatus.State.FAILED.getValue();
        }
    }

    @Override
    public void setConf(Configuration conf) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}



