package com.ssm.nowgo.service;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Advert;


public interface AdvertService {
    PageInfo<Advert> getAdvertList(int pageNum, int pageSize);
}
