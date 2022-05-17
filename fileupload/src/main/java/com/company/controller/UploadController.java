package com.company.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("업로드 폼 요청");
	}

	/*
	 * 파일 하나 첨부시 사용 방법
	 * 
	 * @PostMapping("/uploadForm") public void uploadPost(MultipartFile uploadFile)
	 * { log.info("File Upload Name - "+uploadFile.getOriginalFilename());
	 * log.info("File Upload Size - "+uploadFile.getSize());
	 * 
	 * String uploadPath ="e:upload"; File file = new
	 * File(uploadPath,uploadFile.getOriginalFilename());
	 * 
	 * try { uploadFile.transferTo(file); } catch (IllegalStateException e) {
	 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } }
	 */

	
	// 여러개의 파일을 한꺼번에 업로드 할 경우
	// 배열로 받고, for문을 이용해 반복문을 사용해야 함
	@PostMapping("/uploadForm") 
	public void uploadPost(MultipartFile[] uploadFile) {
		String uploadPath = "e:/upload";

		for (MultipartFile multipartFile : uploadFile) {

			log.info("File Upload Name - " + multipartFile.getOriginalFilename());
			log.info("File Upload Size - " + multipartFile.getSize());

			File file = new File(uploadPath, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	//다운로드											
	//MediaType.APPLICATION_OCTET_STREAM_VALUE : 다운로드가 가능한 타입
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName) {
		log.info("download file name : "+fileName);
		
		//서버 폴더에 접근해서 해당 파일 가져오기
		Resource resource = new FileSystemResource("e:/upload/"+fileName);
		String resourceUidName = resource.getFilename();
		
		//다운로드 할 때 UUID 값 제거하기
		String resourceName = resourceUidName.substring(resourceUidName.indexOf("_")+1);
		
		//헤더에 추가하기
		HttpHeaders headers = new HttpHeaders();
		try {																	//한글처리를 위한 인코딩 작업
			headers.add("Content-Disposition", "attachment;fileName="+new String(resourceName.getBytes("utf-8"),"iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
	@GetMapping("/uploadFormAjax")
	public void uploadFormAjax() {
		log.info("업로드 폼 요청");
	}
	
	@PostMapping("/uploadFormAjax")
	public void uploadFormAjaxPost() {
		log.info("업로드 요청");
	}
	
	
}









