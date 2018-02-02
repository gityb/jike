package com.ssm.nowgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.ssm.nowgo.dao.AdvertDao;
import com.ssm.nowgo.pojo.Advert;
import com.ssm.nowgo.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    private AdvertDao advertDao;

    @Override
    public PageInfo<Advert> getAdvertList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Advert> advertList =advertDao.getAdvertList();
        return new PageInfo<>(advertList);
    }
}
