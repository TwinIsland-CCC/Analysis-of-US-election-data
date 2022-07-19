package com.nd.auoe.mr;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 联系人
 */
public class Contact implements DBWritable, Writable {
    private String tell;
    private String name;
    //序列化
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(tell);
        out.writeUTF(name);
    }
    //反序列化
    @Override
    public void readFields(DataInput in) throws IOException {
        tell=in.readUTF();
        name=in.readUTF();
    }
    //数据库写入操作
    @Override
    public void write(PreparedStatement statement) throws SQLException {
        statement.setString(1,this.tell);
        statement.setString(2,this.name);
    }
    //数据库读取数据操作
    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.tell=resultSet.getString("tell");
        this.name=resultSet.getString("name");
    }

    public Contact() {
    }

    public Contact(String tell, String name) {
        this.tell = tell;
        this.name = name;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

