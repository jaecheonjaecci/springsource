package ch2;

public class TvUser {

	public static void main(String[] args) {

		//결합력을 낮추는 방식 : 다형성
		TV tv = new SamsungTv();
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();
	}

}
