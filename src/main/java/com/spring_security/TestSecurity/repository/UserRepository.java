package com.spring_security.TestSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_security.TestSecurity.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	//가입 로직: username 중복이 있는지 검증(커스텀 구문)
	boolean existsByUsername(String username);
	
	//로그인시 username 찾는 로직
	UserEntity findByUsername(String username);

}
