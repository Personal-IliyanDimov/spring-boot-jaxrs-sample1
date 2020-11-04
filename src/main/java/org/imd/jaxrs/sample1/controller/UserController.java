package org.imd.jaxrs.sample1.controller;

import lombok.RequiredArgsConstructor;
import org.imd.jaxrs.sample1.model.dto.UserDto;
import org.imd.jaxrs.sample1.model.mapper.UserMapper;
import org.imd.jaxrs.sample1.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/users")
    List<UserDto> users() {
        return userMapper.toUserDtos(userService.findAll());
    }
}
