package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity(name = "tbl_boards")
public class BoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long bno;
	
	private String title;
	
	private String writer;
	
	private String content;
	
	@CreationTimestamp //작성하는 시간 자동으로 변경
	private LocalDateTime regdate;
	
	@UpdateTimestamp //업데이트 시간 자동으로 변경
	private LocalDateTime updatedate;
	
}
