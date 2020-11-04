package org.imd.jaxrs.sample1.model.mapper;

import org.imd.jaxrs.sample1.model.domain.User;
import org.imd.jaxrs.sample1.model.dto.UserDto;
import org.imd.jaxrs.sample1.model.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserEntity userEntity);
    UserDto toUserDto(User userEntity);

    List<User> toUsers(List<UserEntity> userEntities);
    List<UserDto> toUserDtos(List<User> users);
}
