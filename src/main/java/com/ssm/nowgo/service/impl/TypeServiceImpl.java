package com.ssm.nowgo.service.impl;

import com.ssm.nowgo.dao.TypeDao;
import com.ssm.nowgo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {
@Autowired
private TypeDao typeDao;
    @Override
    public int count() {
        return typeDao.count();
    }
}
