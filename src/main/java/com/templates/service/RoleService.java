package com.templates.service;

import com.templates.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByUserId(Long userId);
}