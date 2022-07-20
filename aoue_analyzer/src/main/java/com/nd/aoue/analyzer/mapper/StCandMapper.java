package com.nd.aoue.analyzer.mapper;

import com.nd.aoue.common.constant.Parties;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class StCandMapper extends TableMapper<Text, Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Mapper.Context context)
            throws IOException, InterruptedException {

        String rowKey = Bytes.toString(key.get());
        System.out.println(rowKey);
        //String[] values = rowKey.split("_");

        //获取HBase表中的数据
        String cand_nm = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("cand_nm")));

        String contbr_st = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contbr_st")));

        String contb_receipt_amt = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contb_receipt_amt")));

        String contb_receipt_dt = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contb_receipt_dt")));

        System.out.println(cand_nm);

        String k = contb_receipt_dt + "^" + contbr_st +  "^" + cand_nm;

        //将数据写给reducer
        context.write(new Text(k), new Text(contb_receipt_amt));
    }
}
