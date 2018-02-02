package com.ssm.nowgo.service;

import com.ssm.nowgo.pojo.User;

import java.util.List;
import java.util.Map;

public interface LoginService {
    //判断手机号是否已注册
     int getUser(User user);
    //注册
     void addUser(User user);//发送短信验证将手机号和验证码插入数据库
    void updateUser(User user);//验证码一致，修改数据库信息
    List<User> getUserByPhone(String code);//注册时通过phone查询，判断验证码是否一致
    //登录
    User getUserByUser(User user);//登录时通过密码登录
    //登录时获取验证码
    public void updateCode(User user);//获取验证码时将数据库数据改变

    User getUserById(int id);
    void updateUserInfo(User user);
    void addLoginLog(Map map);
}
