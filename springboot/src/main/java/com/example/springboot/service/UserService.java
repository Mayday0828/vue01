package com.example.springboot.service;

import com.example.springboot.entity.User;
import org.springframework.stereotype.Service;


/**
 * user service注入到spring容器中
 */
@Service

public class UserService {

    public void insertUser(User user){

    }

    public User login(User user) {
        return null;
    }

    public User register(User user) {
        return null;
    }
}
