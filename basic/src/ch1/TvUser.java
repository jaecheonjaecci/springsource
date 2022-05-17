package ch1;

public class TvUser {

	public static void main(String[] args) {
//		SamsungTv tv = new SamsungTv();
		
//		tv.powerOn();
//		tv.volumUp();
//		tv.volumDown();
//		tv.powerOff();

		LgTv tv = new LgTv();
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();
	}

}
