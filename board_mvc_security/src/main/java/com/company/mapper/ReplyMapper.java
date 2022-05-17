package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;

public interface ReplyMapper {

	public int insert(ReplyDTO insertDto);
	public ReplyDTO get(int rno);
	public int update(ReplyDTO updateDto);
	public int delete(int rno);
	public List<ReplyDTO> list(@Param("cri") Criteria cri, @Param("bno") int bno);
	public int getCountByBno(int bno);
	public int deleteAll(int bno);
}
