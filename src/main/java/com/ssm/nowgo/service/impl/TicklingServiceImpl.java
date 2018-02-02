package com.ssm.nowgo.service.impl;

import com.ssm.nowgo.dao.TicklingDao;
import com.ssm.nowgo.pojo.Tickling;
import com.ssm.nowgo.service.TicklingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicklingServiceImpl implements TicklingService {
    @Autowired
    private TicklingDao ticklingDao;
    @Override
    public void addTickling(Tickling tickling) {
        ticklingDao.addTickling(tickling);
    }
}


