package com.company.app;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;
import com.company.service.MemberService;

public class MemberClient {

	public static void main(String[] args) {

		// 스프링 컨테이너 로드
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");

		// 서비스 메소드 호출
		MemberService service = (MemberService) ctx.getBean("service");

		Scanner sc = new Scanner(System.in);
		boolean flag = true;

		while (flag) {
			System.out.println("====================================================");
			System.out.println("1. 전체 멤버 조회");
			System.out.println("2. 특정 멤버 조회");
			System.out.println("3. 특정 멤버 수정");
			System.out.println("4. 특정 멤버 삭제");
			System.out.println("5. 특정 멤버 추가");
			System.out.println("6. 프로그램 종료");
			System.out.println("====================================================");

			System.out.print("메뉴 >> ");
			int no = Integer.parseInt(sc.nextLine());

			switch (no) {
			case 1:

				System.out.println();

				List<MemberDTO> list = service.getList();
				System.out.println("아이디\t 성명\t 성별\t 이메일");
				System.out.println("----------------------------------------");
				for (MemberDTO dto : list) {
					System.out.print(dto.getUserid() + "\t");
					System.out.print(dto.getName() + "\t");
					System.out.print(dto.getGender() + "\t");
					System.out.print(dto.getEmail() + "\n");

				}
				System.out.println();

				break;
			case 2:
				System.out.println();
				System.out.println("조회할 사용자 정보 입력");
				System.out.println("==================================");
				System.out.print("조회할 멤버 아이디 >>");
				String userid = sc.nextLine();
				System.out.print("조회할 멤버 비밀번호 >>");
				String password = sc.nextLine();
				
				MemberDTO dto = service.getRow(userid, password);
				System.out.println();
				System.out.println("조회한 사용자 정보는 다음과 같습니다.");
				System.out.println("=========================================");
				System.out.print("userid : "+dto.getUserid() + "\n");
				System.out.print("name : "+dto.getName() + "\n");
				System.out.print("gender : "+dto.getGender() + "\n");
				System.out.print("email : "+dto.getEmail());
				System.out.println();

				break;
			case 3:

				System.out.println();
				System.out.println("수정할 사용자 정보 입력");
				System.out.println("==================================");
				System.out.print("아이디 >>");
				userid = sc.nextLine();
				System.out.print("비밀번호 >>");
				password = sc.nextLine();
				System.out.print("수정할 비밀번호 >>");
				String confirm_password = sc.nextLine();
	
				ChangeDTO changeDto = new ChangeDTO();
				changeDto.setUserid(userid);
				changeDto.setPassword(password);
				changeDto.setConfirm_password(confirm_password);
				
				System.out.println("수정 결과는 다음과 같습니다.");
				System.out.println(service.updateMember(changeDto)?"수정성공":"수정실패");
				
				break;
			case 4:
				System.out.println();
				System.out.println("삭제할 사용자 정보 입력");
				System.out.println("==================================");
				System.out.print("아이디 >>");
				userid = sc.nextLine();
				System.out.print("비밀번호 >>");
				password = sc.nextLine();
				
				System.out.println("삭제 결과는 다음과 같습니다.");
				System.out.println(service.deleteMember(userid, password)?"삭제성공":"삭제실패");

				break;
			case 5:
				MemberDTO insertdDto = new MemberDTO();
				System.out.println();
				System.out.println("입력할 사용자 정보 입력");
				System.out.println("======================================");
				System.out.print("아이디 >>");
 				insertdDto.setUserid(sc.nextLine());
 				System.out.print("비밀번호 >>");
 				insertdDto.setPassword(sc.nextLine());
 				System.out.print("이름 >>");
 				insertdDto.setName(sc.nextLine());
 				System.out.print("성별 >>");
 				insertdDto.setGender(sc.nextLine());
 				System.out.print("이메일 >>");
 				insertdDto.setEmail(sc.nextLine());
 				
				System.out.println("입력 결과는 다음과 같습니다.");
 				System.out.println(service.insertMember(insertdDto)?"입력성공":"입력실패");
 
				break;
			case 6:
				flag = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}

	}

}
