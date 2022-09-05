package com.chen.spike.c_service;

import com.chen.spike.a_domain.User;
import com.chen.spike.b_repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

}
