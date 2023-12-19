package com.example.springboot.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Page;
import com.example.springboot.entity.Monkey;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.MonkeyMapper;
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

public class MonkeyService extends ServiceImpl<MonkeyMapper, Monkey> {

    //    @Autowired
    @Resource
    MonkeyMapper monkeyMapper;

//    public Monkey selectByUsername(String leader) {
//        QueryWrapper<Monkey> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", leader);  //  eq => ==   where username = #{username}
//        // 根据用户名查询数据库的用户信息
//        return getOne(queryWrapper); //  select * from user where username = #{username}
//    }

    // 验证用户账户是否合法
//    public Monkey login(Monkey monkey) {
//        User dbUser = selectByUsername(monkey.getUsername());
//        if (dbUser == null) {
//            // 抛出一个自定义的异常
//            throw new ServiceException("用户名或密码错误");
//        }
//        if (!user.getPassword().equals(dbUser.getPassword())) {
//            throw new ServiceException("用户名或密码错误");
//        }
//        // 生成token
//        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
//        dbUser.setToken(token);
//        return dbUser;
//    }
//
//    public User register(User user) {
//        User dbUser = selectByUsername(user.getUsername());
//        if (dbUser != null) {
//            // 抛出一个自定义的异常
//            throw new ServiceException("用户名已存在");
//        }
//        user.setName(user.getUsername());
//        userMapper.insert(user);
//        return user;
//    }
//
//    public void resetPassword(User user) {
//        User dbUser = selectByUsername(user.getUsername());
//        if (dbUser == null) {
//            // 抛出一个自定义的异常
//            throw new ServiceException("用户不存在");
//        }
//        if (!user.getPhone().equals(dbUser.getPhone())) {
//            throw new ServiceException("验证错误");
//        }
//        dbUser.setPassword("123");   // 重置密码
//        updateById(dbUser);
//    }
}
