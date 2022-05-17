package com.company.tvapp;

public class LgTv implements TV {
   
	private Speaker speaker;
	
	public LgTv() {
		System.out.println("LgTv 객체 생성");
	}
	
	public LgTv(Speaker speaker) {
		super();
		this.speaker = speaker;
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

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