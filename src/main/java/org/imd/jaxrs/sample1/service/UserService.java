package org.imd.jaxrs.sample1.service;

import lombok.RequiredArgsConstructor;
import org.imd.jaxrs.sample1.model.domain.User;
import org.imd.jaxrs.sample1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.;
    };

}
