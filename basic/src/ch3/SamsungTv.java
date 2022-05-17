package ch3;

public class SamsungTv implements TV{
	@Override
	public void turnOn() {
		System.out.println("SamsungTv - 파워 On");
		
	}
	@Override
	public void turnOff() {
		System.out.println("LgTv - 파워 Off");
		
	}
	@Override
	public void soundUp() {
		System.out.println("LgTv - 볼륨 Up");
		
	}
	@Override
	public void soundDown() {
		System.out.println("LgTv - 볼륨 Down");
		
	}
}
