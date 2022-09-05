package com.chen.spike.d_controller;

import com.chen.spike.a_domain.User;
import com.chen.spike.c_service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody User user) {
        userService.save(user);
    }

}
