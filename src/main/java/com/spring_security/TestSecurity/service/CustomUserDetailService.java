package com.spring_security.TestSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring_security.TestSecurity.dto.CustomUserDetails;
import com.spring_security.TestSecurity.entity.UserEntity;
import com.spring_security.TestSecurity.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	//추후에 생성자 주입으로 바꿀 것
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//user name으로 userData를 찾음
		UserEntity userData = userRepository.findByUsername(username);
        System.out.println("CustomUserDetailService: " + userData);
        
        //CustomUserDetails에 userData를 주입
        if (userData != null) {
            return new CustomUserDetails(userData);
        }
        System.out.println("CustomUserDetailService  -> CustomUserDetails: " + userData);

        return null;
	}

	
}
