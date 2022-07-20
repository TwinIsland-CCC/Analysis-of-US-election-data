package com.nd.aoue.analyzer.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class testreducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //统计各党派支持
        int OccupationAmount=0;
        for (Text value : values) {
            OccupationAmount+=1;
        }
        System.out.println(key + "^" + OccupationAmount);

        context.write(key,new Text(key + "^" + OccupationAmount));

    }
}
