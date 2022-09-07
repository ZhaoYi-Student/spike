package com.chen.spike.o_config.security;

import com.chen.spike.c_service.UserDetailsServiceImpl;
import com.chen.spike.o_common.ResponseEntity;
import com.chen.spike.o_filter.AuthenticationRequestFilter;
import com.chen.spike.o_toolkits.ResponseUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/authentication", "/register").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf().disable()
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(applicationContext.getBean(UserDetailsServiceImpl.class))
                .addFilterBefore(
                        applicationContext.getBean(AuthenticationRequestFilter.class),
                        UsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling().authenticationEntryPoint(
                        (request, response, authException)
                                -> ResponseUtil.writeResponse(
                                request, response,
                                ResponseEntity.fail(authException.getMessage())
                        )
                )
        ;
        // @formatter:on
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
