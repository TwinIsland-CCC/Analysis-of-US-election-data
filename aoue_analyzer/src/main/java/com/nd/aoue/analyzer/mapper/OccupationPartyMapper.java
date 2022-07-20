package com.nd.aoue.analyzer.mapper;

import com.nd.aoue.common.constant.Parties;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//不同职业对两党派的支持情况
public class OccupationPartyMapper extends TableMapper<Text, Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Mapper.Context context)
            throws IOException, InterruptedException {

        String rowKey= Bytes.toString(key.get());
        System.out.println(rowKey);
        //String[] values = rowKey.split("_");

        //获取HBase表中的数据
        String cand_nm = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("cand_nm")));

        String contbr_occupation = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contbr_occupation")));

        String contb_receipt_amt = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contb_receipt_amt")));
        System.out.println(cand_nm);

        String party = Parties.getParty(String.valueOf(cand_nm));
        String k = party + "^" + contbr_occupation;

        //将数据写给reducer
        context.write(new Text(k),new Text(contb_receipt_amt));


        //TODO 本组化
/*      context.write(new Text(call1+"_"+year),new Text(duration));//主叫年
        context.write(new Text(call1+"_"+month),new Text(duration));//主叫月
        context.write(new Text(call1+"_"+day),new Text(duration));//主叫日

        context.write(new Text(call2+"_"+year),new Text(duration));//被叫年
        context.write(new Text(call2+"_"+month),new Text(duration));//被叫月
        context.write(new Text(call2+"_"+day),new Text(duration));//被叫日*/

    }
}
