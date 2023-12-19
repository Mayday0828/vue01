package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Monkey;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface MonkeyMapper extends BaseMapper<Monkey> {
}
