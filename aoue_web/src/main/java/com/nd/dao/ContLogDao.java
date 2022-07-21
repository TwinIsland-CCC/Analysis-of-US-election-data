package com.nd.dao;

//访问献金日志持久层

import com.nd.entity.ContAmount;
import com.nd.entity.ContLog;
import com.nd.entity.OccuAmountLog;
import com.nd.entity.OccuAmountLog2;

import java.util.List;
import java.util.Map;

public interface ContLogDao {
    //查询献金日志
    List<ContLog> findContLog(Map<String, Object> map);

    //查询人数
    List<ContAmount> findContAmount(Map<String, Object> map1);

    //查询职业
    List<OccuAmountLog> findOccuAmount(Map<String, Object> map2);

    //查询职业2
    List<OccuAmountLog2> findOccuAmount2(Map<String, Object> map3);
}
