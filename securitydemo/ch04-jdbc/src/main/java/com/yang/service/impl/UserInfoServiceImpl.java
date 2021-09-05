package com.yang.service.impl;

import com.yang.dao.UserInfoDao;
import com.yang.entity.UserInfo;
import com.yang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao dao;
    @Override
    public UserInfo findUserInfo(String username) {
        UserInfo userInfo = dao.findByUsername(username);
        return userInfo;
    }
}
