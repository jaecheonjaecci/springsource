package com.company.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum; //페이지 번호
	private int amount; //한페이지 당 보여줄 게시물 수
	
	private String type;
	private String keyword;
	
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		
		//타입이 널이면 String 배열을 생성해서 리턴
		//널이 아니면 {'?'} 이 안에 물음표로 넣어서 리턴
		
		//ex) 제목 or 내용 or 작성자 => TCW => {'T','C','W'}
		return type==null?new String[] {}:type.split("");
}
}
