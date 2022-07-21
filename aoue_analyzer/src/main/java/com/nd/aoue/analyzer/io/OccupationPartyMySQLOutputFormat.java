package com.nd.aoue.analyzer.io;

import com.nd.aoue.common.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.PathOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.PathOutputCommitterFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OccupationPartyMySQLOutputFormat extends OutputFormat<Text, Text> {
    protected static class MySQLRecordWriter extends RecordWriter<Text,Text> {
        private Connection connection=null;
        public MySQLRecordWriter(){
            //获取资源
            connection= JDBCUtil.getConnection();
        }
        /**
         * 输出数据
         * @param key
         * @param value
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void write(Text key, Text value) throws IOException, InterruptedException {
            //System.out.println("123");
            System.out.println(value);
            String[] values=value.toString().split("\\^");
            String party = values[0];
            String occupation = values[1];
            int OccupationAmount = Integer.parseInt(values[2]);
            PreparedStatement ps=null;
            //TODO 本组化 现在插入的是各职业对各党派的献金总额
            try {
                String insertSQL="insert into occupation_party (party, occupation, amount) values (?, ?, ?);";
                ps = connection.prepareStatement(insertSQL);
                ps.setString(1, party);
                ps.setString(2, occupation);
                ps.setInt(3, OccupationAmount);
                ps.executeUpdate();//这里会把数据输出到mysql中
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 释放资源
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new OccupationPartyMySQLOutputFormat.MySQLRecordWriter();
    }

    @Override
    public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {

    }
    private PathOutputCommitter committer = null;
    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null: new Path(name);
    }
    //提交mapreduce给hadoop
    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(context);
            committer = PathOutputCommitterFactory.getCommitterFactory(
                    output,
                    context.getConfiguration()).createOutputCommitter(output, context);
        }
        return committer;
    }
}
