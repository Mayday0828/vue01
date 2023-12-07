package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     *新增用户信息
     * （前端调用control方法）
     **/
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        /**
         * 返回一个成功的标识给前端
         */
        userService.insertUser(user);
        return Result.success();

    }
}
