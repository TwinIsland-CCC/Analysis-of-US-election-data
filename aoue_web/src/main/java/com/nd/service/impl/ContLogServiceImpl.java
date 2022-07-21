package com.nd.service.impl;

//献金日志业务层实现类

import com.nd.dao.ContLogDao;
import com.nd.entity.ContAmount;
import com.nd.entity.ContLog;
import com.nd.entity.OccuAmountLog;
import com.nd.entity.OccuAmountLog2;
import com.nd.service.ContLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service//业务层
public class ContLogServiceImpl implements ContLogService{
    @Autowired
    private ContLogDao contLogDao;

    @Override
    public List<ContLog> findContLog(String candidate, String contDate) {
        Map<String,Object> map=new HashMap<>();
        map.put("candidate",candidate);
        map.put("contDate",contDate);
        List<ContLog> contLog = contLogDao.findContLog(map);
        return contLog;
    }

    @Override
    public List<ContAmount> findContAmount(String candidate, String contDate) {
        Map<String,Object> map1=new HashMap<>();
        map1.put("candidate",candidate);
        map1.put("contDate",contDate);
        List<ContAmount> contAmount = contLogDao.findContAmount(map1);
        return contAmount;
    }

    @Override
    public List<OccuAmountLog> findOccuAmount() {
        Map<String,Object> map2=new HashMap<>();
        List<OccuAmountLog> ContOccuAmount = contLogDao.findOccuAmount(map2);
        return ContOccuAmount;
    }

    @Override
    public List<OccuAmountLog2> findOccuAmount2() {
        Map<String,Object> map3=new HashMap<>();
        List<OccuAmountLog2> ContOccuAmount2 = contLogDao.findOccuAmount2(map3);
        return ContOccuAmount2;
    }
}
