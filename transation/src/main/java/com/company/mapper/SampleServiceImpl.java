package com.company.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.service.SampleService;

@Service("sample")
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleMapper mapper1;
	
	@Autowired
	private SampleMapper mapper2;
	

	@Transactional //밑에 작업을 하나의 작업으로 처리하기 위함
	public void addDate(String data) {
		mapper1.insertCol1(data);
		
		mapper2.insertCol1(data);
		

	}

}
