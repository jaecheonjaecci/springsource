package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.mapper.BoardAttachMapper;
import com.company.mapper.BoardMapper;
import com.company.mapper.ReplyMapper;

@Service
public class BoardServiceImpi implements BoardService {

	@Autowired
	private BoardMapper mapper;
	@Autowired
	private BoardAttachMapper boardAttachMapper;
	@Autowired
	private ReplyMapper replyMapper;

	@Transactional
	public boolean register(BoardDTO insertDto) {

		// 게시물 등록 : 첨부파일은 등록을 안할 수도 있기 때문에 먼저 시행
		boolean result = mapper.insert(insertDto) > 0 ? true : false;

		// 첨부파일 등록 : null이거나 사이즈가 0보다 작거나 같은 경우 리턴
		if (insertDto.getAttachList() == null || insertDto.getAttachList().size() <= 0) {
			return false;
		}

		insertDto.getAttachList().forEach(attach -> {
			attach.setBno(insertDto.getBno());
			boardAttachMapper.insert(attach);
		});

		return result;
	}

	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return mapper.listAll(cri);
	}

	@Override
	public BoardDTO getRow(int bno) {
		return mapper.read(bno);
	}

	@Transactional
	public boolean modify(BoardDTO updateDto) {

		// 기존 첨부파일 삭제
		boardAttachMapper.deleteAll(updateDto.getBno());

		// 게시물 수정
		boolean modifyResult = mapper.update(updateDto) == 1;

		//첨부파일 삭제 후 업데이트를 할 때 첨부파일이 없으면 리턴하도록 하기
		if (updateDto.getAttachList() == null || updateDto.getAttachList().size() <= 0) {
			return modifyResult;
		}

		// 첨부파일이 있었다면, (bno를 넣어주고) 첨부파일 하나 당 insert를 하기
		if (modifyResult && updateDto.getAttachList().size() > 0) {
			updateDto.getAttachList().forEach(attach -> {
				attach.setBno(updateDto.getBno());
				boardAttachMapper.insert(attach);
			});
		}

		return modifyResult;
	}

	@Transactional
	public boolean delete(int bno) {
		//댓글 삭제
		replyMapper.deleteAll(bno);
		
		// 첨부파일 삭제
		boardAttachMapper.deleteAll(bno);

		return mapper.delete(bno) > 0 ? true : false;
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachFileDTO> findByBno(int bno) {
		return boardAttachMapper.read(bno);
	}

	@Override
	public boolean attachRemove(int bno) {
		// 실행 후 0 or 1이 넘어오는데 1과 같으면 true가 넘어가고 0이 넘어오면 false가 넘어감
		return boardAttachMapper.deleteAll(bno) == 1;
	}

}
