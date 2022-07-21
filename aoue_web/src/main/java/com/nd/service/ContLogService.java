package com.nd.service;

//访问献金日志业务层借口

import com.nd.entity.ContAmount;
import com.nd.entity.ContLog;
import com.nd.entity.OccuAmountLog;
import com.nd.entity.OccuAmountLog2;

import java.util.List;

public interface ContLogService {
    //查询献金记录日志
    List<ContLog> findContLog(String candidate, String contDate);

    //查询人数日志
    List<ContAmount> findContAmount(String candidate, String contDate);

    //查询职业日志
    List<OccuAmountLog> findOccuAmount();

    List<OccuAmountLog2> findOccuAmount2();
}
