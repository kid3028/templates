package com.templates.dao;

import com.mybatis.MyMapper;
import com.templates.entity.User;

public interface UserMapper extends MyMapper<User> {
    User getUserByUserName(String userName);
}