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
    private volatile boolean flg = true;  // 为增强线程可见性，添加volatile，线程共享

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
        List<ElectAllData> read = in.read(ElectAllData.class);
/*        for (Contact c : read){
            System.out.println(c);
        }*/

        int i = 0;
        while (i < read.size()) {
            ElectAllData inData = read.get(i++);  // 获取主叫电话

            //转换日期格式
            //System.out.println(inData.getContb_receipt_dt());
            String parsedDate = DateUtil.parse(inData.getContb_receipt_dt());

            ElectData outData =
                    new ElectData(inData.getCand_nm(),
                    inData.getContbr_nm(), inData.getContbr_st(),
                    inData.getContbr_employer(), inData.getContbr_occupation(),
                    inData.getContb_receipt_amt(), parsedDate);

            //通话记录写出到文件中
            try {
                out.write(outData);
                System.out.println(i);
                //System.out.println(outData);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*
/*            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

             */
        }
    }

    //释放资源
    @Override
    public void close() throws IOException {
        if (in != null) in.close();
        if (out != null) out.close();
    }
}
