package com.chen.spike.d_controller;

import com.chen.spike.a_domain.User;
import com.chen.spike.c_service.UserDetailsServiceImpl;
import com.chen.spike.c_service.UserServiceImpl;
import com.chen.spike.o_common.ResponseEntity;
import com.chen.spike.o_model.request.AuthenticationRequestModel;
import com.chen.spike.o_toolkits.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.register(user);
    }

    @PostMapping("/flush")
    public void flush(@RequestBody User user) {
        userService.flush(user);
    }

    @PostMapping("/authentication")
    public ResponseEntity<String> authentication(@RequestBody AuthenticationRequestModel authenticationRequestModel) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestModel.getUserName(), authenticationRequestModel.getUserPassword()
                    )
            );
        } catch (BadCredentialsException exception) {
            log.info(exception.getMessage());
            throw new BadCredentialsException("username or password is bad ");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestModel.getUserName());
        String token = JwtUtil.generatorToken(userDetails);
        return ResponseEntity.ok(token);
    }

}
