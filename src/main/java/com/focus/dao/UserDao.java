package com.focus.dao;

import java.util.List;

import com.focus.po.User;

public interface UserDao {
    User selectById(String id);

    List<User> selectAll();

    User selectByUserName(String username);

    void insert(User user);

    int update(User user);

    int deleteById(String id);
}
