package com.tom.mybatisstudy.mapper;


import com.tom.mybatisstudy.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAllUsers();

    User getUserById(Integer id);
}
