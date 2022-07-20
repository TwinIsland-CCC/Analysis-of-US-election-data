package com.nd.aoue.analyzer.reducer;

import com.nd.aoue.common.constant.Parties;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 分析数据Reducer
 */
public class SupportReducer extends Reducer<Text, Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //统计总捐款人数
        int sumAmount=0;
        for (Text value : values) {
            // int amount=Integer.parseInt(value.toString());
            sumAmount++;
        }


        System.out.println(key + "^" + sumAmount);

        context.write(key,new Text(key + "^" + sumAmount));
    }
}