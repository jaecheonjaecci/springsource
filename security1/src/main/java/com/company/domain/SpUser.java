package com.company.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpUser {
	private String userid;
	private String email;
	private boolean enabled;
	private String password;
	
	//롤 정보 담기, 역할은 여러개가 있을 수 있어서 리스트로 담음
	private List<SpUserAuthority> authorities;
}
