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
        if (Objects.nonNull(user.getId())) {
            throw new IllegalStateException("User param must be with id null.");
        }

        Optional<UserEntity> existingUserEntityOptional = userRepository.findByIcnAndType(user.getIcn(), user.getType());
        if (existingUserEntityOptional.isPresent()) {
            throw new UserAlreadyExistsException(user.getIcn(), user.getType());
        }

        UserEntity userEntity = userMapper.toUserEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.toUser(savedUserEntity);
    }

    public User updateUser(Long id, User user) throws UserNotFoundException, UserNotUpdatedException {
        Optional<UserEntity> existingUserEntityOptional = userRepository.findById(user.getId());
        existingUserEntityOptional.orElseThrow(() -> new UserNotFoundException(user.getId()));

        UserEntity existingUserEntity = existingUserEntityOptional.get();
        if (! id.equals(user.getId())) {
            throw new IllegalStateException("Parameter {id} value is different from {user.id} value.");
        }

        userMapper.transfer(user, existingUserEntity);

        try {
            UserEntity savedUserEntity = userRepository.save(existingUserEntity);
            return userMapper.toUser(savedUserEntity);
        } catch (RuntimeException re) {
            throw new UserNotUpdatedException(re);
        }
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        boolean exists = userRepository.existsById(id);
        if (! exists) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    public User findUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> existingUserEntityOptional = userRepository.findById(id);
        existingUserEntityOptional.orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toUser(existingUserEntityOptional.get());
    }
}
