package com.nd.aoue.producer.bean;

import com.nd.aoue.common.bean.DataIn;
import com.nd.aoue.common.bean.DataOut;
import com.nd.aoue.common.bean.Producer;
import com.nd.aoue.common.util.DateUtil;
import com.nd.aoue.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

//local file producer
public class LocalFileProducer implements Producer {
    private DataIn in;
    private DataOut out;
    public static volatile boolean flg = true;  // 为增强线程可见性，添加volatile，线程共享

    @Override
    public void setIn(DataIn in) {
        this.in = in;
    }

    @Override
    public void setOut(DataOut out) {
        this.out = out;
    }

    //生产数据
    @Override
    public void produce() throws IOException {
        List<ElectAllData> read = null;
        while((read = in.readPart(ElectAllData.class)).size() != 0){
            if(flg){
                int i = 0;
                while (i < read.size()) {
                    ElectAllData inData = read.get(i++);  // 获取
                    //转换日期格式
                    String parsedDate = DateUtil.parse(inData.getContb_receipt_dt());
                    //System.out.println(parsedDate);
                    ElectData outData =
                            new ElectData(inData.getCand_nm(),
                                    inData.getContbr_nm(), inData.getContbr_st(),
                                    inData.getContbr_employer(), inData.getContbr_occupation(),
                                    inData.getContb_receipt_amt(), parsedDate);
                    //写出到文件中
                    try {
                        out.write(outData);
                        //System.out.println(i);
                        System.out.println(outData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //释放资源
    @Override
    public void close() throws IOException {
        if (in != null) in.close();
        if (out != null) out.close();
    }
}
