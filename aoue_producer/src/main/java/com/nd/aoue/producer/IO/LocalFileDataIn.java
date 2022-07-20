package com.nd.aoue.producer.IO;

import com.nd.aoue.common.bean.Data;
import com.nd.aoue.common.bean.DataIn;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LocalFileDataIn implements DataIn {
    private BufferedReader reader = null;  // 字符输入流

    public LocalFileDataIn(String s) {
        setPath(s);
    }

    /**
     * 读取数据，返回对象
     * @return 返回对象
     * @throws IOException IO异常
     */
    @Override
    public Object read() throws IOException {

        return null;
    }

    /**
     * 分批读入数据，防止炸内存
     * @return
     * @throws IOException
     */
    @Override
    public <T extends Data> List<T> readPart(Class<T> tClass) throws IOException {
        List<T> set = new ArrayList<>();
        //从本地数据文件中读取数据
        String line = null;
        int i = 0;
        int interval = 100;
        while ((line = reader.readLine()) != null && i++ < interval){
            try {
                // get instance object
                T t = tClass.newInstance();
                // 把读取的数据放入实例
                t.setValue(line);
                // 把实例对象放入集合
                set.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return set;
    }

    /**
     * 读取数据
     * @param tClass
     * @param <T>
     * @return 集合
     * @throws IOException IO异常
     */
    @Override
    public <T extends Data> List<T> read(Class<T> tClass) throws IOException {
        List<T> set = new ArrayList<>();
        //从本地数据文件中读取数据
        String line = null;
        while ((line = reader.readLine()) != null){
            try {
                // get instance object
                T t = tClass.newInstance();
                // 把读取的数据放入实例
                t.setValue(line);
                // 把实例对象放入集合
                set.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return set;
    }

    @Override
    public void setPath(String path) {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    //释放资源
    @Override
    public void close() throws IOException {
        if (reader != null) reader.close();
    }
}
