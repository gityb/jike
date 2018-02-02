package com.ssm.nowgo.service.impl;


import com.ssm.nowgo.dao.IconsDao;
import com.ssm.nowgo.pojo.Icons;
import com.ssm.nowgo.service.IconsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IconsServiceImpl implements IconsService {
    @Autowired
    private IconsDao iconsDao;
    @Override
    public List<Icons> getIconsList() {
        return iconsDao.getIconsList();
    }
}
