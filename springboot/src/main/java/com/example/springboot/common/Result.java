package com.example.springboot.common;


/**
 * 添加统一返回对象 Result
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor  //全类型的所有属性的构造函数
@NoArgsConstructor   //创建无参构造函数
@Builder
public class Result {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";
    public static final String CODE_SYS_ERROR = "500";

    /**
     * 请求的返回编码  200  401  404  500
     * 编码表示请求是否成功或失败
     * 或者说 可以看出失败对象是什么
     */
    private String code;
    /**
     * msg表示错误信息的详情
     */
    private String msg;
    /**
     * 数据从什么地方返回出去
     * object 是java 的父类
     * data是后端返回前端的数据载体
     */
    private Object data;


    public static Result success() {
        return new Result(CODE_SUCCESS, "请求成功", null);
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "请求成功", data);
    }

    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR, msg, null);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error() {
        return new Result(CODE_SYS_ERROR, "系统错误", null);
    }

}
