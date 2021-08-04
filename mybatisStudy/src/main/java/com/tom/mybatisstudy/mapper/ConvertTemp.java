package com.tom.mybatisstudy.mapper;

import com.tom.mybatisstudy.dto.UserDto;
import com.tom.mybatisstudy.entity.User;

/**
 * @author wangtao
 * @date 2021/8/4
 */
public class ConvertTemp {

    public static void main(String[] args) {
        User user = new User(1,"17",12,"22");
        UserDto userDto = UserConvertMapper.INSTANCE.convertUserDto(user);
        System.out.println(userDto);
    }
}
