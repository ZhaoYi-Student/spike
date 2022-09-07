package com.chen.spike.c_service;

import com.chen.spike.a_domain.User;
import com.chen.spike.b_repository.UserRepository;
import com.chen.spike.o_ex.UserNameAlreadyExistException;
import com.chen.spike.o_toolkits.AssertUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        AssertUtil.isNotEmpty(user.getUserId(), "user id is must be null !");
        log.info("encode password for user : {}", user.getUserName());
        checkExist(user);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
    }

    public void flush(User user) {
        AssertUtil.isEmpty(user.getUserId(), "user id is must not be null !");
        userRepository.save(user);
    }

    public void checkExist(User user) {
        final String userName = user.getUserName();
        User byUserName = userRepository.findByUserName(userName);
        AssertUtil.isNotEmpty(byUserName, new UserNameAlreadyExistException("username is already exist"));
    }

}
