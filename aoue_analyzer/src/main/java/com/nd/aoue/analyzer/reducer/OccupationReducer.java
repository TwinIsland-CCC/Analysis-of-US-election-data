package com.nd.aoue.analyzer.reducer;

import com.nd.aoue.common.constant.Parties;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 分析数据Reducer
 */
public class OccupationReducer extends Reducer<Text, Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //统计总捐款金额
        int sumAmount=0;
        for (Text value : values) {
            int amount=(int)Float.parseFloat(value.toString());
            sumAmount+=amount;
        }
        System.out.println(key + "^"
             //   + Parties.getParty(String.valueOf(key)) + "_"
                + sumAmount);

        context.write(key,new Text(key + "^"
          //      + Parties.getParty(String.valueOf(key)) + "_"
                + sumAmount));
    }
}