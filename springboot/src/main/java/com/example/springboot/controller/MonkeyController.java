package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.AuthAccess;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Monkey;
import com.example.springboot.entity.User;
import com.example.springboot.service.MonkeyService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/monkey")
public class MonkeyController {
    @Autowired
    MonkeyService monkeyService;

    /**
     *新增用户信息(post请求方法)
     * （前端调用control方法）
     * 返回一个成功的标识给前端
     **/
    @AuthAccess
    @PostMapping("/add")
    public Result add(@RequestBody Monkey monkey){
        monkeyService.save(monkey);
        return Result.success();
    }

    /**
     * 修改用户信息(put请求)
     */
    @AuthAccess
    @PutMapping("/update")
    public Result update(@RequestBody Monkey monkey) {
        monkeyService.updateById(monkey);
        return Result.success();
    }

    /**
     * 删除单个用户信息
     */
    @AuthAccess
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        monkeyService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除用户信息
     */
    @AuthAccess
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        monkeyService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部用户信息
     */
    @AuthAccess
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Monkey> monkeyList = monkeyService.list(new QueryWrapper<Monkey>().orderByDesc("id"));
        return Result.success(monkeyList);
    }

    /**
     * 根据ID查询用户信息
     */
    @AuthAccess
    @GetMapping("/selectAllById/{id}")
    public Result selectAll(@PathVariable Integer id) {
        Monkey monkey = monkeyService.getById(id);
        return Result.success(monkey);
    }

//    /**
//     * 根据name查询用户信息
//     * 根据条件查询，统一返回List对象集合
//     */
//    @GetMapping("/selectAllByName/{name}")
//    public Result selectAllByName(@PathVariable String name) {
//        List<User> userList= userService.selectAllByName(name);
//        return Result.success(userList);
//    }

//    /**
//     * 多条件查询用户信息
//     */
//    @GetMapping("/selectAllByMore")
//    public Result selectAllByMore(@RequestParam String username,@RequestParam String name) {
//        List<User> userList= userService.selectAllByMore(username,name);
//        return Result.success(userList);
//    }

//    /**
//     * 多条件模糊查询用户信息
//     */
//    @GetMapping("/selectAllByMo")
//    public Result selectAllByMo(@RequestParam String username,@RequestParam String name) {
//        List<User> userList= userService.selectAllByMo(username,name);
//        return Result.success(userList);
//    }

    /**
     * 多条件分页模糊查询用户信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @AuthAccess
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String leader,
                               @RequestParam String name) {
        QueryWrapper<Monkey> queryWrapper =new QueryWrapper<Monkey>().orderByDesc("id");
        queryWrapper.like(StrUtil.isNotBlank(leader),"leader",leader);
        queryWrapper.like(StrUtil.isNotBlank(name),"name",name);

        Page<Monkey> page = monkeyService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(page);
    }
}
