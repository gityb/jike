package com.ssm.nowgo.service.impl;

import com.ssm.nowgo.dao.ScoreDao;
import com.ssm.nowgo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public void addScore(Double point) {
        scoreDao.addScore(point);
    }
}
