package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
//@TableName("monkey")
public class Monkey {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String leader;
    private String name;
    private String health;
    private double weight;

//    @TableField(exist = false)
//    private String token;
}
