package com.chen.spike.c_service;

import com.chen.spike.a_domain.User;
import com.chen.spike.b_repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("username not found by " + username);
        }

        User user = userRepository.findByUserName(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("username not found by " + username);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(passwordEncoder.encode(user.getUserPassword()))
                .roles("ROLE_USER")
                .build();

    }
}
