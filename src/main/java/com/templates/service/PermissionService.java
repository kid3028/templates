package com.templates.service;

import com.templates.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getAll();

    List<Permission> getPermsByRoleId(Long roleId);

} 