package com.spring_security.TestSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_security.TestSecurity.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
