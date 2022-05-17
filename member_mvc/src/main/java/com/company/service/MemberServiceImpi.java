package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;
import com.company.mapper.MemberMapper;

@Service
public class MemberServiceImpi implements MemberService {

	@Autowired
	private MemberMapper mapper;

	@Override
	public boolean register(MemberDTO memberDTO) {
		return mapper.insert(memberDTO) > 0 ? true : false;
	}

	@Override
	public MemberDTO dupId(String userid) {
		return mapper.selectById(userid);
	}

	@Override
	public LoginDTO login(LoginDTO loginDTO) {
		return mapper.login(loginDTO);
	}

	@Override
	public boolean changePwd(ChangeDTO changeDTO) {
		return mapper.update(changeDTO) > 0 ? true : false;
	}

	@Override
	public boolean leave(LoginDTO leaveDTO) {
		return mapper.delete(leaveDTO) > 0 ? true : false;
	}

}
