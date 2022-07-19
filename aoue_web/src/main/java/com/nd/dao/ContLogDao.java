package com.nd.dao;

//访问献金日志持久层

import com.nd.entity.ContLog;

import java.util.List;
import java.util.Map;

public interface ContLogDao {
    //查询献金日志
    List<ContLog> findContLog(Map<String,Object> map);
}
