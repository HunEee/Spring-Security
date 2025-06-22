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
		
       //db에 이미 동일한 username을 가진 회원이 존재하는지 여부
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if (isUser) {
            return;
        }
        /*추가 인증 로직
          	아이디의 자리수
			아이디의 특수문자 포함 불가
			admin과 같은 아이디 사용 불가
			비밀번호 자리수
			비밀번호 특수문자 포함 필수

         */
        
        
		System.out.println("JoinService: 데이터 바인딩");
		
        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword())); //비밀번호 암호화
        data.setRole("ROLE_USER");


        userRepository.save(data);
        System.out.println("JoinService: 레퍼지토리 저장");
	}
	
}
