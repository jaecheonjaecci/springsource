package ch3;

public class LgTv implements TV {
	
//	private SonySpeaker speaker = new SonySpeaker();
	private Speaker speaker; 
	//이런 방식은 has a 관계
	//스피커를 사용할 경우 무조건 초기화가 필요함
	
	//1. 멤버변수 초기화를 위한 생성자 생성
	public LgTv(Speaker speaker) {
	super();
	this.speaker = speaker;
}
	
	public LgTv() {
		
	}
	
	//2. 멤버 변수 초기화를 위한 setter 메소드 생성
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
	//	System.out.println("LgTv - 볼륨 Up");
	// speaker가 null인데 voulmeup을 불러서 nullpointexception
	// 객체 생성이 필요
	//	speaker = new SonySpeaker();
		speaker.volumUp();
	}
	@Override
	public void soundDown() {
	//	System.out.println("LgTv - 볼륨 Down");
	// speaker가 null인데 voulmedown을 불러서 nullpointexception
	// up에는 객체 생성이 되어있지만 down이 먼저 불렸기 때문에 객체생성이 되지 않아
	// 널포인터가 남
	//	speaker = new SonySpeaker();
		speaker.volumDown();
	}
}
