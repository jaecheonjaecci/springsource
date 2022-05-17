package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.SpUser;
import com.company.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public boolean register(SpUser user) {

		// 회원 정보 등록 전 비밀번호 암호화
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// 회원가입 : 부모키이기 때문에 먼저 시행
		boolean result = mapper.register(user) == 1;
		
		// 회원 role 삽입
//		mapper.register_auth(user.getUserid(), "ROLE_ADMIN");
		mapper.register_auth(user.getUserid(), "ROLE_USER");
		
		return result;
	}

}
