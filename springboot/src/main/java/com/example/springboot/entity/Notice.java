package com.example.springboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/**
 * 功能：
 * 作者：小华
 * 日期： 2023/12/18 15:44
 */
@Data
public class Notice {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer userid;
    private String time;
    private String open;

    // 这个注解表示这个字段不在数据库表里  是用来做业务处理用的
    @TableField(exist = false)
    private String user;

}
