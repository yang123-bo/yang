package com.yang.mapper;

import com.yang.entity.SysRole;

import java.util.List;

public interface SysRoleMapper {

    List<SysRole> selectRoleByUser(Integer userId);

}
