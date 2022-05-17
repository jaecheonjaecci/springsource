package com.company.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	// 페이지 나누기와 관련된 정보를 담는 객체

	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;

	private int total; // 전체 게시물 수

	private Criteria cri; //이 안에 페이지 번호와 페이지에 보여줄 데이터 갯수가 들어있음

	public PageDTO(Criteria cri, int total) {
		this.total = total;
		this.cri = cri;

		//7페이지를 클릭하면 시작페이지와 마지막 페이지를 구하는 식
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;

		int realEnd = (int) (Math.ceil((total / 1.0) / cri.getAmount()));

		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
