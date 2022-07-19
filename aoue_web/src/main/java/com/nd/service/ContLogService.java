package com.nd.service;

//访问献金日志业务层借口

import com.nd.entity.ContLog;

import java.util.List;

public interface ContLogService {
    //查询献金记录日志
    List<ContLog> findContLog(String candidate,String contDate);

}
