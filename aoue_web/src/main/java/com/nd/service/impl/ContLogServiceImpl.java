package com.nd.service.impl;

//献金日志业务层实现类

import com.nd.dao.ContLogDao;
import com.nd.entity.ContLog;
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
}
