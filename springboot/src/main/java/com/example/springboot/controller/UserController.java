package com.example.springboot.controller;

import com.example.springboot.common.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     *新增用户信息(post请求方法)
     * （前端调用control方法）
     * 返回一个成功的标识给前端
     **/
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        userService.insertUser(user);
        return Result.success();
    }

    /**
     * 修改用户信息(put请求)
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    /**
     * 删除单个用户信息
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return Result.success();
    }

    /**
     * 批量删除用户信息
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        userService.batchDeleteUser(ids);
        return Result.success();
    }

    /**
     * 查询全部用户信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> userList = userService.selectAll();
        return Result.success(userList);
    }

    /**
     * 根据ID查询用户信息
     */
    @GetMapping("/selectAllById/{id}")
    public Result selectAll(@PathVariable Integer id) {
        User user = userService.selectAllById(id);
        return Result.success(user);
    }

    /**
     * 根据name查询用户信息
     * 根据条件查询，统一返回List对象集合
     */
    @GetMapping("/selectAllByName/{name}")
    public Result selectAllByName(@PathVariable String name) {
        List<User> userList= userService.selectAllByName(name);
        return Result.success(userList);
    }

    /**
     * 多条件查询用户信息
     */
    @GetMapping("/selectAllByMore")
    public Result selectAllByMore(@RequestParam String username,@RequestParam String name) {
        List<User> userList= userService.selectAllByMore(username,name);
        return Result.success(userList);
    }

    /**
     * 多条件模糊查询用户信息
     */
    @GetMapping("/selectAllByMo")
    public Result selectAllByMo(@RequestParam String username,@RequestParam String name) {
        List<User> userList= userService.selectAllByMo(username,name);
        return Result.success(userList);
    }

    /**
     * 多条件分页模糊查询用户信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String name) {
        Page<User> page = userService.selectByPage(pageNum, pageSize, username, name);
        return Result.success(page);
    }
}
