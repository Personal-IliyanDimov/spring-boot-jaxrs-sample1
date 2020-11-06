package org.imd.jaxrs.sample1.service;

import lombok.RequiredArgsConstructor;
import org.imd.jaxrs.sample1.exception.UserNotUpdatedException;
import org.imd.jaxrs.sample1.exception.UserAlreadyExistsException;
import org.imd.jaxrs.sample1.exception.UserNotFoundException;
import org.imd.jaxrs.sample1.model.domain.User;
import org.imd.jaxrs.sample1.model.entity.UserEntity;
import org.imd.jaxrs.sample1.model.mapper.UserMapper;
import org.imd.jaxrs.sample1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<User> findAll() {
        final List<UserEntity> userEntities = userRepository.findAll();
        Optional.ofNullable(userEntities)
            .map(userMapper::toUsers).orElse(Collections.emptyList());
        return userMapper.toUsers(userEntities);
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        if (Objects.isNull(user.getId())) {
            throw new IllegalStateException("User param must be without id.");
        }

        boolean userExists = userRepository.existsById(user.getId());
        if (userExists) {
            throw new UserAlreadyExistsException(user.getId());
        }

        UserEntity userEntity = userMapper.toUserEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.toUser(savedUserEntity);
    }

    public User updateUser(User user) throws UserNotFoundException, UserNotUpdatedException {
        Optional<UserEntity> existingUserEntityOptional = userRepository.findById(user.getId());
        existingUserEntityOptional.orElseThrow(() -> new UserNotFoundException(user.getId()));

        UserEntity existingUserEntity = existingUserEntityOptional.get();
        userMapper.transfer(user, existingUserEntity);

        try {
            UserEntity savedUserEntity = userRepository.save(existingUserEntity);
            return userMapper.toUser(savedUserEntity);
        } catch (RuntimeException re) {
            throw new UserNotUpdatedException(re);
        }
    }

    public void deleteUserById(Long id)  {
        userRepository.deleteById(id);
    }

    public User findUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> existingUserEntityOptional = userRepository.findById(id);
        existingUserEntityOptional.orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toUser(existingUserEntityOptional.get());
    }
}
