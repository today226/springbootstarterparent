package com.focus.service.impl;

import com.focus.dao.UserDao;
import com.focus.po.User;
import com.focus.service.UserService;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SimpleUserService implements UserService {


    @Resource
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public User selectById(String id) {
        return userDao.selectById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User selectByUserName(String username) {
        return userDao.selectByUserName(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public String insert(User user) { 
        userDao.insert(user);
        return user.getId();
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user) == 1;
    }

    @Override
    public boolean delete(String id) {
        return userDao.deleteById(id) == 1;
    }
}
