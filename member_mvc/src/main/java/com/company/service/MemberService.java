package com.company.service;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;

public interface MemberService {
	public boolean register(MemberDTO memberDTO);
	public MemberDTO dupId(String userid);
	public LoginDTO login(LoginDTO loginDTO);
	public boolean changePwd(ChangeDTO changeDTO);
	public boolean leave(LoginDTO leaveDTO);
}
