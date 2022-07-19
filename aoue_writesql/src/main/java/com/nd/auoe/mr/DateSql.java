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
 * 将日期写入sql用的类
 */
public class DateSql implements DBWritable, Writable {
    private Integer id;
    private String year;
    private String month;
    private String day;
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(year);
        out.writeUTF(month);
        out.writeUTF(day);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id=in.readInt();
        this.year=in.readUTF();
        this.month=in.readUTF();
        this.day=in.readUTF();
    }

    @Override
    public void write(PreparedStatement statement) throws SQLException {
        statement.setInt(1,this.id);
        statement.setString(2,this.year);
        statement.setString(3,this.month);
        statement.setString(4,this.day);
    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.id=resultSet.getInt("id");
        this.year=resultSet.getString("year");
        this.month=resultSet.getString("month");
        this.day=resultSet.getString("day");
    }

    public DateSql(Integer id, String year, String month, String day) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public DateSql() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

