package com.spring_security.TestSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring_security.TestSecurity.dto.JoinDTO;
import com.spring_security.TestSecurity.entity.UserEntity;
import com.spring_security.TestSecurity.repository.UserRepository;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public void joinProcess(JoinDTO joinDTO) {
		
       //db에 이미 동일한 username을 가진 회원이 존재하는지?
		
		System.out.println("JoinService: 데이터 바인딩");
		
        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword())); //비밀번호 암호화
        data.setRole("ROLE_USER");


        userRepository.save(data);
        System.out.println("JoinService: 레퍼지토리 저장");
	}
	
}
