package com.example.springboot.mapper;


/**
 * 接口，用户接口（mapper搜索数据库）
 * 把interface user mapper注入到容器中
 */
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into 'user' (username,password,name,phone,email,address,avatar) values()")
    void insert(User user);

}
