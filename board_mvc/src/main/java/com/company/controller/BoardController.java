package com.company.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.PageDTO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board/*")
@Log4j2
public class BoardController {

	@Autowired
	private BoardService service;

	// board/register.jsp, void
	@GetMapping("/register")
	public void registerGet() {
		log.info("register 폼 요청");
	}

	// post + /board/register
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto, RedirectAttributes rttr) {
		log.info("register 가져오기 " + insertDto);

		// 첨부파일 확인하기
//		if(insertDto.getAttachList()!=null) {
//			insertDto.getAttachList().forEach(attach -> log.info(attach+""));
//		}

		service.register(insertDto);
//		log.info("bno" + insertDto.getBno());

		// flash로 담을 경우 임시세션을 이용하는 것과 같아서 $로 부를 수 있음
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list";

	}

	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		log.info("전체 리스트 요청 " + cri);

		List<BoardDTO> list = service.getList(cri);

		// 페이지 나누기를 위한 정보 얻기 / totalCnt:전체 데이터 수
		int totalCnt = service.getTotalCount(cri);

		model.addAttribute("pageDto", new PageDTO(cri, totalCnt));
		model.addAttribute("list", list);
	}

	@GetMapping({ "/read", "/modify" })
	// 페이지 나누기를 하면서 넘어오는 pageNum과 amount를 받기 위해 Criteria를 추가함
	public void readGet(int bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("read, modify 요청하기 " + bno);

		BoardDTO dto = service.getRow(bno);

		model.addAttribute("dto", dto);

	}

	@PostMapping("/modify")
	public String modifyPost(BoardDTO modifyDto, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 수정 요청 " + modifyDto + " " + cri);
	
		
		service.modify(modifyDto);

		// 페이지 나누기 값
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());

		// 검색값 보내기
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		// 수정을 한 뒤에도 알림창을 띄우기
		// result값이 아니라 success를 보냈기 때문에 작성할때 내용과는 달리
		// modal-body에 있는 기본 문구가 뜨게 됨("처리가 완료되었습니다.")
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";

	}

	@PostMapping("/remove")
	public String deletePost(int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 삭제 요청 " + bno);

		// 첨부파일 목록 얻어오기
		List<AttachFileDTO> attachList = service.findByBno(bno);

		// 삭제 후 리스트 이동
		if (service.delete(bno)) {
			// 첨부파일 삭제
			deleteFiles(attachList);

			// 페이지 나누기 값
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());

			// 검색값 보내기
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());

			// 삭제를 한 뒤에도 알림창을 띄우기
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list";
	}

	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> getAttachList(int bno) {
		log.info("파일 첨부 가져오기 " + bno);
		return new ResponseEntity<List<AttachFileDTO>>(service.findByBno(bno), HttpStatus.OK);
	}

	private void deleteFiles(List<AttachFileDTO> attachList) {
	      if(attachList == null || attachList.size() == 0) {
	         return;
	      }
	      
	      log.info("파일 삭제 중");
	      
	      attachList.forEach(attach -> {
	         Path file = Paths.get("e:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
	         
	         try {
	            Files.deleteIfExists(file);
	            
	            if(Files.probeContentType(file).startsWith("image")) {
	               Path thumbNail = Paths.get("e:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
	               
	               // 이미지 썸네일 삭제
	               Files.delete(thumbNail);
	            }
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      });
	   }

}