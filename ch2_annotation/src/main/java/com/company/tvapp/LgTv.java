package com.company.tvapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("lg")
public class LgTv implements TV {
   
	@Autowired //생성된 객체를 주입
	@Qualifier("apple") //이름 지정 기능
	private Speaker speaker;
	
	public LgTv() {
		System.out.println("LgTv 객체 생성");
	}
	
//	public LgTv(Speaker speaker) {
//		super();
//		this.speaker = speaker;
//	}
//	
//	public void setSpeaker(Speaker speaker) {
//		this.speaker = speaker;
//	}

	@Override
	public void turnOn() {
		System.out.println("LgTv - 전원 On");
		
	}

	@Override
	public void turnOff() {
		System.out.println("LgTv - 전원 Off");
		
	}

	@Override
	public void soundUp() {
		speaker.volumeUp();
		
	}

	@Override
	public void soundDown() {
		speaker.volumeDown();
		
	}
}