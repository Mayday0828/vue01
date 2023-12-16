package com.example.springboot.service;

import cn.hutool.core.util.RandomUtil;
import com.example.springboot.common.Page;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * user service注入到spring容器中
 */
@Service

public class UserService {

    @Autowired
    UserMapper userMapper;
    public void insertUser(User user){

        userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    public void batchDeleteUser(List<Integer> ids) {
        for (Integer id : ids){
            userMapper.deleteUser(id);
        }
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User selectAllById(Integer id) {
        return userMapper.selectAllById(id);
    }


    public List<User> selectAllByName(String name) {
        return userMapper.selectAllByName(name);
    }

    public List<User> selectAllByMore(String username, String name) {
        return userMapper.selectAllByMore(username,name);
    }

    public List<User> selectAllByMo(String username, String name) {
        return userMapper.selectAllByMo(username,name);
    }

    public Page<User> selectByPage(Integer pageNum, Integer pageSize, String username, String name) {
        Integer skipNum = (pageNum - 1) * pageSize;  // 计算出来  1 -> 0,5    2 -> 5,5   3 -> 10,5

        Page<User> page = new Page<>();
        List<User> userList = userMapper.selectByPage(skipNum, pageSize, username, name);
        Integer total = userMapper.selectCountByPage(username, name);
        page.setTotal(total);
        page.setList(userList);
        return page;
    }

    // 验证用户账户
    public User login(User user) {
        // 根据用户名查询
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        //生成token
        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }
    public User register(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser != null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户已存在");
        }
        user.setName("honey-" + RandomUtil.randomNumbers(4));
        userMapper.insert(user);
        return user;
    }

}
