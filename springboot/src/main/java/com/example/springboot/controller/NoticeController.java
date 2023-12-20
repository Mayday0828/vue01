package com.example.springboot.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.AuthAccess;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Notice;
import com.example.springboot.entity.User;
import com.example.springboot.service.NoticeService;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 功能：
 * 作者：小华
 * 日期： 2023/12/1815:42
 */
@RestController
@RequestMapping("/index/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;

    /**
     * 新增信息
     */
    @AuthAccess
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        System.out.println(notice);
//        User currentUser = TokenUtils.getCurrentUser();  // 获取到当前登录的用户信息
//        notice.setUserid(currentUser.getId());
        notice.setTime(DateUtil.now());  //   2023-09-12 21:09:12
        noticeService.save(notice);
        return Result.success();
    }

    /**
     * 修改信息
     */
    @AuthAccess
    @PutMapping("/update")
    public Result update(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return Result.success();
    }

    /**
     * 删除信息
     */
    @AuthAccess
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        noticeService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除信息
     */
    @AuthAccess
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        noticeService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部信息
     */
    @AuthAccess
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Notice> userList = noticeService.list(new QueryWrapper<Notice>().orderByDesc("id"));
        return Result.success(userList);
    }

    /**
     * 根据ID查询信息
     */
    @AuthAccess
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Notice notice = noticeService.getById(id);
        User user = userService.getById(notice.getUserid());
        if (user != null) {
            notice.setUser(user.getName());
        }
        return Result.success(notice);
    }


    /**
     * 多条件模糊查询信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @AuthAccess
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String title) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<Notice>().orderByDesc("id");  // 默认倒序，让最新的数据在最上面
        queryWrapper.like(StrUtil.isNotBlank(title), "title", title);
        Page<Notice> page = noticeService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<Notice> records = page.getRecords();
//        List<User> list = userService.list();
        for (Notice record : records) {
            Integer authorid = record.getUserid();
            User user = userService.getById(authorid);
//            String author = list.stream().filter(u -> u.getId().equals(authorid)).findFirst().map(User::getName).orElse("");
            if (user != null) {
                record.setUser(user.getName());
            }
        }
        return Result.success(page);
    }
}
