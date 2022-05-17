package ch3;

public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("AppleSpeaker 객체 생성");
	}
	@Override
	public void volumUp() {
		System.out.println("AppleSpeaker volum Up");

	}

	@Override
	public void volumDown() {
		System.out.println("AppleSpeaker volum Down");

	}

}
