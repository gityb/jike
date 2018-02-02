package com.ssm.nowgo.service.impl;

import com.ssm.nowgo.dao.LoginDao;
import com.ssm.nowgo.pojo.User;
import com.ssm.nowgo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginDao loginDao;

    @Override
    public int getUser(User user) {
        return loginDao.getUser(user);
    }

    @Override
    public List<User> getUserByPhone(String code) {
        return loginDao.getUserByPhone(code);
    }

    @Override
    public void addUser(User user) {
        loginDao.addUser(user);
    }

    @Override
    public User getUserById(int id) {
        return loginDao.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        loginDao.updateUser(user);
    }

    @Override
    public User getUserByUser(User user) {
       return loginDao.getUserByUser(user);
    }

    @Override
    public void updateCode(User user) {
        loginDao.updateCode(user);
    }

    @Override
    public void updateUserInfo(User user) {
        loginDao.updateUserInfo(user);
    }

    @Override
    public void addLoginLog(Map map) {
        loginDao.addLoginLog(map);
    }
}

