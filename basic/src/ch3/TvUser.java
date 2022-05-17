package ch3;

public class TvUser {

	public static void main(String[] args) {

		//결합력을 낮추는 방식 : 다형성
//		TV tv = new LgTv(new SonySpeaker());
		
		TV tv = new LgTv();
//		tv.setSpeaker(new SonySpeaker());
//		tv.setSpeaker(new AppleSpeaker());
		tv.turnOn();
		tv.soundDown();
		tv.soundUp();
		tv.turnOff();
		
		TV tv1 = new LgTv();
		
		System.out.println(tv==tv1 ? "객체 동등" : "객체 다름");
	}

}
