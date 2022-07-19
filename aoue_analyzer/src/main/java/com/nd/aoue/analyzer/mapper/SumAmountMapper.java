package com.nd.aoue.analyzer.mapper;

import com.nd.aoue.common.constant.Names;
import org.apache.commons.collections.ArrayStack;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * 分析数据mapper
 */
public class SumAmountMapper extends TableMapper<Text,Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context)
            throws IOException, InterruptedException {

        String rowKey= Bytes.toString(key.get());
        System.out.println(rowKey);
        //String[] values = rowKey.split("_");

        //获取HBase表中的数据
        String cand_nm = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("cand_nm")));
/*
        String contbr_nm = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contbr_nm")));
        String contbr_st = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contbr_st")));
        String contbr_employer = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contbr_employer")));
        String contbr_occupation = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contbr_occupation")));
*/
        String contb_receipt_amt = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contb_receipt_amt")));
/*
        String contb_receipt_dt = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contb_receipt_dt")));
*/

        System.out.println(cand_nm);

        //将数据写给reducer
        context.write(new Text(cand_nm),new Text(contb_receipt_amt));//写给它的是总金额数据


        //TODO 本组化
/*      context.write(new Text(call1+"_"+year),new Text(duration));//主叫年
        context.write(new Text(call1+"_"+month),new Text(duration));//主叫月
        context.write(new Text(call1+"_"+day),new Text(duration));//主叫日

        context.write(new Text(call2+"_"+year),new Text(duration));//被叫年
        context.write(new Text(call2+"_"+month),new Text(duration));//被叫月
        context.write(new Text(call2+"_"+day),new Text(duration));//被叫日*/

    }
}
