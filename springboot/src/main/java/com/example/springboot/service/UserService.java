package com.example.springboot.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Page;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.getOne;
import static com.baomidou.mybatisplus.extension.toolkit.Db.updateById;


/**
 * user service注入到spring容器中
 */
@Service

public class UserService extends ServiceImpl<UserMapper,User> {

//    @Autowired
    @Resource
    UserMapper userMapper;
    public User selectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);  //  eq => ==   where username = #{username}
        // 根据用户名查询数据库的用户信息
        return getOne(queryWrapper); //  select * from user where username = #{username}
    }
    // 验证用户账户是否合法
    public User login(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        // 生成token
        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public User register(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser != null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名已存在");
        }
        user.setName(user.getUsername());
        userMapper.insert(user);
        return user;
    }

    public void resetPassword(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户不存在");
        }
        if (!user.getPhone().equals(dbUser.getPhone())) {
            throw new ServiceException("验证错误");
        }
        dbUser.setPassword("123");   // 重置密码
        updateById(dbUser);
    }
//    public void insertUser(User user){
//
//        userMapper.insert(user);
//    }
//
//    public void updateUser(User user) {
//        userMapper.updateUser(user);
//    }
//
//    public void deleteUser(Integer id) {
//        userMapper.deleteUser(id);
//    }
//
//    public void batchDeleteUser(List<Integer> ids) {
//        for (Integer id : ids){
//            userMapper.deleteUser(id);
//        }
//    }
//
//    public List<User> selectAll() {
//        return userMapper.selectAll();
//    }
//
//    public User selectAllById(Integer id) {
//        return userMapper.selectAllById(id);
//    }
//
//
//    public List<User> selectAllByName(String name) {
//        return userMapper.selectAllByName(name);
//    }
//
//    public List<User> selectAllByMore(String username, String name) {
//        return userMapper.selectAllByMore(username,name);
//    }
//
//    public List<User> selectAllByMo(String username, String name) {
//        return userMapper.selectAllByMo(username,name);
//    }
//
//    public Page<User> selectByPage(Integer pageNum, Integer pageSize, String username, String name) {
//        Integer skipNum = (pageNum - 1) * pageSize;  // 计算出来  1 -> 0,5    2 -> 5,5   3 -> 10,5
//
//        Page<User> page = new Page<>();
//        List<User> userList = userMapper.selectByPage(skipNum, pageSize, username, name);
//        Integer total = userMapper.selectCountByPage(username, name);
//        page.setTotal(total);
//        page.setList(userList);
//        return page;
//    }

    // 验证用户账户
//    public User login(User user) {
//        //queryWrapper条件查询器
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("username",user.getUsername());
//        // 根据用户名查询
//        User dbUser = userMapper.selectOne(queryWrapper);//select * from user where username =#{username}
//        if (dbUser == null) {
//            // 抛出一个自定义的异常
//            throw new ServiceException("用户名或密码错误");
//        }
//        if (!user.getPassword().equals(dbUser.getPassword())) {
//            throw new ServiceException("用户名或密码错误");
//        }
//        //生成token
//        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
//        dbUser.setToken(token);
//        return dbUser;
//    }
//    public User register(User user) {
//        User dbUser = userMapper.selectByUsername(user.getUsername());
//        if (dbUser != null) {
//            // 抛出一个自定义的异常
//            throw new ServiceException("用户已存在");
//        }
//        user.setName("honey-" + RandomUtil.randomNumbers(4));
//        userMapper.insert(user);
//        return user;
//    }

}
