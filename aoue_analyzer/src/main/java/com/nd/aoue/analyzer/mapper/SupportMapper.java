package com.nd.aoue.analyzer.mapper;

import com.nd.aoue.common.constant.Names;
import com.nd.aoue.common.constant.Parties;
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
public class SupportMapper extends TableMapper<Text,Text> {

    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context)
            throws IOException, InterruptedException {

        String rowKey= Bytes.toString(key.get());

        //获取HBase表中的数据
        String cand_nm = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("cand_nm")));

        String contbr_nm = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contbr_nm")));


        String contb_receipt_dt = Bytes.toString(value.getValue(
                Bytes.toBytes("cand"),
                Bytes.toBytes("contb_receipt_dt")));

        String party = Parties.getParty(String.valueOf(cand_nm));
        String k =cand_nm+ "^"+contb_receipt_dt + "^" + party;
        //将数据写给reducer
        context.write(new Text(k),new Text(contbr_nm));//写给它的是总人数




    }
}
