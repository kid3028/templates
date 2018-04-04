package com.templates.dao;

import com.mybatis.MyMapper;
import com.templates.entity.Permission;

import java.util.List;

public interface PermissionMapper extends MyMapper<Permission> {
    List<Permission> getAll();

    List<Permission> getPermsByRoleId(Long roleId);
}