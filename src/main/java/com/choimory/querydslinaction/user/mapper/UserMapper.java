package com.choimory.querydslinaction.user.mapper;

import com.choimory.querydslinaction.user.dto.response.UserResponseDto;
import com.choimory.querydslinaction.user.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserResponseDto toDto(User entity);
    List<UserResponseDto> toDtos(List<User> entities);
}
