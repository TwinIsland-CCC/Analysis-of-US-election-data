package com.nd.aoue.consumer.bean;

import com.nd.aoue.common.bean.BaseHBaseDao;
import com.nd.aoue.common.constant.Names;
import com.nd.aoue.common.constant.ValueConstant;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

import static com.nd.aoue.common.util.CSVUtil.split;

/**
 * HBase存储数据用，实现消费
 */
public class HBaseDao extends BaseHBaseDao {

    public void init() throws IOException {
        start();  // 开始
        createNamespaceNX(Names.NAMESPACE.getValue());
        createTableXX(Names.TABLE.getValue(),
                ValueConstant.REGION_COUNT,
                Names.CF_CAND.getValue());
        end();  // 结束
    }

    public void insertData(String value) throws IOException {
        //将通话日志保存到HBase表中
        //1.获取通话日志数据
        String[] values = split(value);
        String cand_nm=values[0];  // 候选人名字
        String contbr_nm=values[1];  // 投票人名字
        String contbr_st=values[2];  // 投票人所在州
        String contbr_employer=values[3];  // 投票人的雇佣者
        String contbr_occupation=values[4];  // 投票人职业
        String contb_receipt_amt=values[5];  // 投票人捐赠金额
        String contb_receipt_dt=values[6];  // 投票日期
        //2.创建数据对象
    /*
       rowKey设计
       (1)长度原则
            最大长值64KB,推荐长为10~100byte
            最好8的倍数,能短则短，如果rowkey太长会影响存储性能
       (2)唯一原则:rowKey应该具备唯一性
       (3)散列原则
            盐值散列：不能使用时间戳直接作为rowKey,会导致数据倾斜,在rowkey前加随机数
            字符串反转:可以在时间戳反转,用的最多的是地方在时间戳和电话号码
                      15623513131=>13131532651
            计算分区号:让分区号没有规律就可以,hashMap
    */
        //rowKey=(4+6)+3+2+3+6
        String rowKey=genRegionNum(contbr_nm,contb_receipt_dt)+"_"+contbr_nm.substring(0,3)+"_"
                +contbr_st+"_"+getcontbr_occupation(contbr_occupation)+"_"+contb_receipt_dt;
        Put put=new Put(Bytes.toBytes(rowKey));
        byte[] family=Bytes.toBytes(Names.CF_CAND.getValue());
        //增加列
        put.addColumn(family,Bytes.toBytes("cand_nm"),Bytes.toBytes(cand_nm));
        put.addColumn(family,Bytes.toBytes("contbr_nm"),Bytes.toBytes(contbr_nm));
        put.addColumn(family,Bytes.toBytes("contbr_st"),Bytes.toBytes(contbr_st));
        put.addColumn(family,Bytes.toBytes("contbr_employer"),Bytes.toBytes(contbr_employer));
        put.addColumn(family,Bytes.toBytes("contbr_occupation"),Bytes.toBytes(contbr_occupation));
        put.addColumn(family,Bytes.toBytes("contb_receipt_amt"),Bytes.toBytes(contb_receipt_amt));
        put.addColumn(family,Bytes.toBytes("contb_receipt_dt"),Bytes.toBytes(contb_receipt_dt));
        //3.保存数据
        putData(Names.TABLE.getValue(),put);

    }
}
