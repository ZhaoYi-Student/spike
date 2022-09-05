package com.chen.spike.b_repository;

import com.chen.spike.a_domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}