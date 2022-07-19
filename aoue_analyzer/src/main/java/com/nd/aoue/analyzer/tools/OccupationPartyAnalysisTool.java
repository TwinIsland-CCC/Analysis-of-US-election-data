package com.nd.aoue.analyzer.tools;

import com.nd.aoue.analyzer.io.OccupationPartyMySQLOutputFormat;
import com.nd.aoue.analyzer.mapper.OccupationPartyMapper;
import com.nd.aoue.analyzer.reducer.OccupationPartyReducer;
import com.nd.aoue.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

public class OccupationPartyAnalysisTool implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        //获取job
        Job job= Job.getInstance();
        //设置jar位置
        job.setJarByClass(OccupationPartyAnalysisTool.class);
        //扫描主叫列族
        Scan scan=new Scan();
        //TODO 本组化
        scan.addFamily(Bytes.toBytes(Names.CF_CAND.getValue()));
        //设置mapper
        TableMapReduceUtil.initTableMapperJob(
                Names.TABLE.getValue(),
                scan,
                OccupationPartyMapper.class,
                Text.class,
                Text.class,
                job
        );
        //设置reducer
        job.setReducerClass(OccupationPartyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //设置输出
        job.setOutputFormatClass(OccupationPartyMySQLOutputFormat.class);
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
