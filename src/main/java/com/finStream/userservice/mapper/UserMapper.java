package com.finStream.userservice.mapper;

import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.dto.UserRequest;
import com.finStream.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDtoToUser(UserDto userDto);
    @Mapping(source = "id", target = "id")
    UserDto mapUserToUserDto(User user);
    UserDto mapUserRequestToUserDto(UserRequest userRequest);
    User mapUserRequestToUser(UserRequest userRequest);
}
