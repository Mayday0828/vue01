package com.example.springboot.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：
 * 作者：小华
 * 日期： 2023/12/1020:48
 */
public class JwtInterceptor implements HandlerInterceptor{
//1、拿到token
//2、验证token有没有
//3、拿到userId
//4、在数据查询userId合法
//5、验证user有没有
//6、通过user拿到密码password生成验证器
//7、通过验证器验证token
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");//前端header里面传过来的参数
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");//url参数 token
        }
        // 如果不是映射到方法直接通过
        if (handler instanceof HandlerMethod) {
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if (annotation != null) {//通过handler获取到AuthAccess的注解通过判断注解是不是空，
                return true;
            }
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "请登录");
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);//JWT.decode(token)解码
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "请登录");
        }
        // 根据token中的userid查询数据库
        User user = userMapper.selectAllById(Integer.valueOf(userId));//把字符串转成数字
        if (user == null) {
            throw new ServiceException("401", "请登录");
        }
        // 通过用户密码加密生成签验证器 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token); // 验证token是否合法
        } catch (JWTVerificationException e) {//验证失败
            throw new ServiceException("401", "请登录");
        }
        return true;
    }
}