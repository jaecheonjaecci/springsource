package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter


public class BookDTO {
	private String code;
	private String title;
	private String writer;
	private int price;
}
