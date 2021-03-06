package org.imd.jaxrs.sample1.controller;

import lombok.RequiredArgsConstructor;
import org.imd.jaxrs.sample1.exception.UserAlreadyExistsException;
import org.imd.jaxrs.sample1.exception.UserNotFoundException;
import org.imd.jaxrs.sample1.exception.UserNotUpdatedException;
import org.imd.jaxrs.sample1.model.domain.User;
import org.imd.jaxrs.sample1.model.dto.UserDto;
import org.imd.jaxrs.sample1.model.dto.group.CreateGroup;
import org.imd.jaxrs.sample1.model.dto.group.UpdateGroup;
import org.imd.jaxrs.sample1.model.mapper.UserMapper;
import org.imd.jaxrs.sample1.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDto>> getAllUsers() {
        final List<User> users = userService.findAll();
        return ResponseEntity.ok(userMapper.toUserDtos(users));
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUser(@PathVariable(name = "id") final Long id) throws UserNotFoundException {
        final User user = userService.findUser(id);
        return ResponseEntity.ok(userMapper.toUserDto(user));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> createUser(@RequestBody @Validated(CreateGroup.class) @Valid UserDto userDto) throws UserAlreadyExistsException {
        final User user = userMapper.toUser(userDto);
        final User createdUser = userService.createUser(user);
        return ResponseEntity.ok(userMapper.toUserDto(createdUser));
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") @NotNull Long id,
                                       @RequestBody @Validated(UpdateGroup.class) @Valid UserDto userDto) throws UserNotFoundException, UserNotUpdatedException {
        final User user = userMapper.toUser(userDto);
        final User createdUser = userService.updateUser(id, user);
        return ResponseEntity.ok(userMapper.toUserDto(createdUser));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
