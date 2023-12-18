package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Notice;
import com.example.springboot.mapper.NoticeMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 功能：
 * 作者：小华
 * 日期： 2023/12/1816:06
 */
@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    @Resource
    NoticeMapper NoticeMapper;
}
