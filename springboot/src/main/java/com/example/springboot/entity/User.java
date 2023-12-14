package com.example.springboot.entity;

//数据库连接
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String avatar;
    private String token;
    private String role;



}
