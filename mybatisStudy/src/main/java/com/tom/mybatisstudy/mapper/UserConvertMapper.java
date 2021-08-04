package com.tom.mybatisstudy.mapper;


import com.tom.mybatisstudy.dto.UserDto;
import com.tom.mybatisstudy.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wangtao
 * @date 2021/8/4
 */
@Mapper
public interface UserConvertMapper {
    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    UserDto convertUserDto(User user);
}
