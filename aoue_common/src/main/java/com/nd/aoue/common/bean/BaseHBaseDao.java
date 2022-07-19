package com.nd.aoue.common.bean;

import com.nd.aoue.common.constant.Names;
import com.nd.aoue.common.constant.ValueConstant;
import com.nd.aoue.common.util.DateUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * 基础数据访问对象
 */
public abstract class BaseHBaseDao {
    //创建线程访问对象
    private ThreadLocal<Connection> connHolder = new ThreadLocal<>();
    private ThreadLocal<Admin> adminHolder = new ThreadLocal<>();


    //开始
    protected void start() throws IOException{
        getConnect();//获取connect
        getAdmin();
    }

    //结束
    protected void end() throws IOException {
        Admin admin = getAdmin();
        if(admin != null){
            admin.close();
            adminHolder.remove();
        }
        Connection conn = getConnect();
        if(conn != null){
            conn.close();
            connHolder.remove();
        }
    }
    //获取admin对象
    protected synchronized Admin getAdmin() throws IOException{
        //获取admin对象
        Admin admin = adminHolder.get();
        if(admin == null){
            admin = getConnect().getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }

    //创建连接对象
    protected synchronized Connection getConnect() throws IOException{
        //获取连接对象
        Connection conn = connHolder.get();
        if(conn == null){
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;
    }

    /**
     * 获取查询时startRow, stopRow集合
     * @param tell
     * @param start
     * @param end
     * @return
     */
    protected static List<String[]> getStartRowKeys(String tell, String start, String end){
        List<String[]> rowKeys = new ArrayList<>();

        // 202201000000,

        String startTime = start.substring(0, 6);
        String endTime = end.substring(0, 6);

        // 把日期转化为日历
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(DateUtil.parse(startTime, "yyyyMM"));

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(DateUtil.parse(endTime, "yyyyMM"));

        while(startCal.getTimeInMillis() < endCal.getTimeInMillis()){
            //开始时间小于结束时间
            String now = DateUtil.format(startCal.getTime(), "yyyyMM");
            //获取分区号
            int regionNum = genRegionNum(tell, now);
            //1_12312341234_202207~1_12312341234_202207
            String startRow = regionNum + "_" + tell+"_" + now;
            String endRow = startRow+"|";
            String[] rowKey = {startRow, endRow};
            rowKeys.add(rowKey);
            // 月份+1
            startCal.add(Calendar.MONTH, 1 );

        }
        return rowKeys;
    }

    /**
     * 创建表，没有预分区
     * @param name
     * @param families
     */
    protected void createTableXX(String name,String...families) throws IOException {
        createTableXX(name,null,families);
    }

    /**
     * 创建表，有预分区
     * @param name
     * @param regionCount
     * @param families
     * @throws IOException
     */
    protected void createTableXX(String name,Integer regionCount,String...families) throws IOException {
        Admin admin=getAdmin();
        TableName tableName = TableName.valueOf(name);
        if (admin.tableExists(tableName)) {
            //表存在，删除表
            deleteTable(name);
        }
        //创建表
        createTable(name,regionCount,families);
    }

    /**
     * 创建表
     * @param name
     * @param regionCount
     * @param families
     * @throws IOException
     */
    private void createTable(String name,Integer regionCount,String...families) throws IOException {
        Admin admin=getAdmin();
        TableName tableName = TableName.valueOf(name);
        //表的描述器
        HTableDescriptor hTableDescriptor=
                new HTableDescriptor(tableName);
        //列族
        if(families.length==0||families==null){
            families=new String[1];
            families[0]= Names.CF_INFO.getValue();
        }
        for (String family : families) {
            //列的描述器
            HColumnDescriptor hColumnDescriptor=
                    new HColumnDescriptor(family);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        //增加预分区
        //分区键
        if(regionCount==null||regionCount<=0){//没有分区键
            admin.createTable(hTableDescriptor);
        }else{//有分区键
            byte[][] splitKeys=genSplitKeys(regionCount);
            admin.createTable(hTableDescriptor,splitKeys);
        }
    }

    /**
     * 生成分区键
     * @param regionCount
     * @return
     */
    private static byte[][] genSplitKeys(int regionCount){
        int splitKeyCount=regionCount-1;//如果有6个分区，只有5个分区键
        byte[][] bs=new byte[splitKeyCount][];//写一个二维数组,为分区键
        //分区键:0|,1|,2|,3|,4|
        //[负无穷,0|),[0|,1|),[1|,2|),[2|,3|),[3|,4|),[4|,正无穷)
        List<byte[]> bsList=new ArrayList<byte[]>();
        for (int i = 0; i < splitKeyCount; i++) {
            String splitKey=i+"|";
            //System.out.println(splitKey);
            bsList.add(Bytes.toBytes(splitKey));
        }
        bsList.toArray(bs);
        return bs;
    }

    protected void putData(String name, Put put) throws IOException {
        Connection connect = getConnect();
        Table table = connect.getTable(TableName.valueOf(name));
        table.put(put);
        table.close();
    }

    /**
     * 计算分区号
     * @param contbr_nm
     * @param cand_nm
     * @return
     */
    protected static int genRegionNum(String contbr_nm,String cand_nm){
        //取捐献人姓名前4位
        String contbrnameCode=contbr_nm.substring(0,4);
        //取候选人姓名前4位
        String candnameCode=cand_nm.substring(0,4);
        //实现散列
        int contbrnameCodeHash=contbrnameCode.hashCode();
        int candnameCodeHash=candnameCode.hashCode();
        //crc校验采用异或算法
        int crc=Math.abs(contbrnameCodeHash^candnameCodeHash);
        //取模
        int regionNum=crc% ValueConstant.REGION_COUNT;
        return regionNum;
    }

    /**
     * 删除表
     * @param name
     * @throws IOException
     */
    protected void deleteTable(String name) throws IOException {
        TableName tableName = TableName.valueOf(name);
        Admin admin=getAdmin();
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }
    /**
     * 创建命名空间，如果命名空间已经存在，不需要创建，否则，创建新的
     * @param namespace
     */
    protected void createNamespaceNX(String namespace) throws IOException {
        Admin admin = getAdmin();
        try {
            //获取命名空间描述器
            admin.getNamespaceDescriptor(namespace);
        }catch (NamespaceNotFoundException e){
            //e.printStackTrace();
            //创建命名空间描述器
            NamespaceDescriptor namespaceDescriptor =
                    NamespaceDescriptor.create(namespace).build();
            //创建命名空间
            admin.createNamespace(namespaceDescriptor);
        }

    }



}
