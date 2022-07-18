package com.nd.aoue.consumer.bean;

import com.nd.aoue.common.bean.BaseHBaseDao;
import com.nd.aoue.common.constant.Names;
import com.nd.aoue.common.constant.ValueConstant;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * HBase存储数据用，实现消费
 */
public class HBaseDao extends BaseHBaseDao {

    public void init() throws IOException {
        start();  // 开始
        createNamespaceNX(Names.NAMESPACE.getValue());
        createTableXX(Names.TABLE.getValue(),
                ValueConstant.REGION_COUNT,
                Names.CF_CALLER.getValue());
        end();  // 结束
    }

    public void insertData(String value) throws IOException {
        //将通话日志保存到HBase表中
        //1.获取通话日志数据
        String[] values = value.split("\t");
        String call1=values[0];
        String call2=values[1];
        String calltime=values[2];
        String duration=values[3];
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
        //rowKey=regionNum+call1+time+call2+duration
        String rowKey=genRegionNum(call1,calltime)+"_"+call1+"_"+calltime+"_"+call2+"_"+duration;
        Put put=new Put(Bytes.toBytes(rowKey));
        byte[] family=Bytes.toBytes(Names.CF_CALLER.getValue());
        //增加列
        put.addColumn(family,Bytes.toBytes("call1"),Bytes.toBytes(call1));
        put.addColumn(family,Bytes.toBytes("call2"),Bytes.toBytes(call2));
        put.addColumn(family,Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
        put.addColumn(family,Bytes.toBytes("duration"),Bytes.toBytes(duration));
        //3.保存数据
        putData(Names.TABLE.getValue(),put);

    }
}
