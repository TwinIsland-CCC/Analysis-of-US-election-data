package com.nd.aoue.producer.IO;

import com.nd.aoue.common.bean.DataOut;

import java.io.*;

public class LocalFileDataOut implements DataOut {
    private PrintWriter writer = null;// output stream

    public LocalFileDataOut(String s) {
        setPath(s);
    }

    @Override
    public void setPath(String path) {
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将对象生成到文件中
     * @param obj
     * @throws Exception
     */
    @Override
    public void write(Object obj) throws Exception {
        write(obj.toString());
    }

    /**
     * 将str生成到文件中
     * @param data
     * @throws Exception
     */
    @Override
    public void write(String data) throws Exception {
        writer.println(data);
        writer.flush();  // 把流中的文件放到文件中
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if (writer != null) writer.close();
    }
}
