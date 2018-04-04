package com.templates.dao;

import com.mybatis.MyMapper;
import com.templates.entity.Role;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {

    List<Role> getRolesByUserId(Long userId);
 }