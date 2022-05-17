package com.company.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileCheckTask {
	/*	 
	 * * <ul>
	 * <li>second</li>
	 * <li>minute</li>
	 * <li>hour</li>
	 * <li>day of month</li>
	 * <li>month</li>
	 * <li>day of week</li>
	 * </ul>
	 * 
	 * fixedDelay : 이전에 실행된 Task의 종료 시간으로부터 정의된 시간만큼
	 * 				지난후 실행(밀리세컨드 단위)
	 * fixedRate : 이전에 실행된 Task의 시작 시간으로부터 정의된 시간만큼
	 * 				지난 후 실행(밀리세컨드 단위)
	 * */
	
	//@Scheduled : 메소드 리턴타입은 void, 파라미터는 갖지 않아야 함
	// 0은 정각을 의미
	
	@Scheduled(cron = "0 * * * * *")
	public void schedulerTest() {
		System.out.println("매 분 1초마다 스케쥴링");
	}
	@Scheduled(fixedDelay = 10000)
	public void schdulerTest2() {
		System.out.println("10초마다 스케쥴링");
	}
}
