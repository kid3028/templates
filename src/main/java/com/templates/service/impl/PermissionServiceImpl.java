package com.templates.service.impl;

import com.templates.dao.PermissionMapper;
import com.templates.entity.Permission;
import com.templates.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> getAll() {
        return permissionMapper.getAll();
    }

    @Override
    public List<Permission> getPermsByRoleId(Long roleId) {
        return permissionMapper.getPermsByRoleId(roleId);
    }
}