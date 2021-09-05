package com.yang.dao;

import com.yang.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo,Long> {
    //按照username查询数据库的工作信息
    UserInfo findByUsername(String username);
}
