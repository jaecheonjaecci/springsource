package com.company.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class UploadAjaxController {

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

		// 서버 폴더에 첨부 파일 저장
		String uploadFolder = "e:\\upload";
		String uploadFileName = "";

		// 첨부파일 목록 리스트 생성
		List<AttachFileDTO> attachList = new ArrayList<AttachFileDTO>();

		// upload 폴더 결정
		String uploadFolderPath = getFolder();

		// 폴더 만들기 "e:upload","2021\12\08"을 받음
		// => "e:upload\\2021\\12\\08 폴더를 만듬
		File uploadPath = new File(uploadFolder, uploadFolderPath);

		// 폴더가 존재하지 않으면
		if (!uploadPath.exists()) {
			// 폴더를 만들기 : mkdir(폴더 하나 생성), mkdirs(폴더 여러개 생성)
			uploadPath.mkdirs();
		}

		for (MultipartFile f : uploadFile) {

			log.info("ajax upload " + f.getOriginalFilename());

			// uuid값을 파일이름에 추가하기
			// 중복된 파일명을 만들지 않게 하기 위해서
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + f.getOriginalFilename();

			File saveFile = new File(uploadPath, uploadFileName);

			AttachFileDTO attachFileDto = new AttachFileDTO();
			attachFileDto.setUuid(uuid.toString());
			attachFileDto.setUploadPath(uploadFolderPath);
			attachFileDto.setFileName(f.getOriginalFilename());

			// 이미지 파일 여부 확인
			if (checkImageType(saveFile)) {
				attachFileDto.setFileType(true);

				// 썸네일 저장
				try {
					// 지정한 폴더에 지정된 이름으로 파일을 사용하기
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					// 파일을 읽어오기 위함 - f에서 읽어옴
					InputStream in = f.getInputStream();
					// 썸네일의 크기 지정
					Thumbnailator.createThumbnail(in, thumbnail, 100, 100);

					// 사용후 닫아주기
					in.close();
					thumbnail.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			attachList.add(attachFileDto);

			try {
				f.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return new ResponseEntity<List<AttachFileDTO>>(attachList, HttpStatus.OK);
	}

	// 썸네일 이미지 가져오기
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("썸네일 요청 " + fileName);

		File file = new File("e:\\upload\\", fileName);

		ResponseEntity<byte[]> result = null;

		HttpHeaders headers = new HttpHeaders();
		// content-type은 브라우저에게 어떤 파일 형식을 보낼 것인지 미리 알려주는 것
		// 미리 알려줘야 브라우저가 적절히 본인의 일을 할 수 있음
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 서버 폴더에 파일을 삭제
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("삭제 파일이름 : " + fileName, "파일타입 : " + type);

		try {
			File file = new File("e:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));
			file.delete(); // 썸네일 이미지, 일반파일 삭제

			// 이미지 파일인 경우 : 원본이미지, 썸네일 이미지를 모두 삭제
			// 일반 파일인 경우 : 원본 파일만 삭제

			if (type.equals("image")) { // 원본 이미지 삭제
				String largeName = file.getAbsolutePath().replace("s_", "");
				new File(largeName).delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// 다운로드
	// MediaType.APPLICATION_OCTET_STREAM_VALUE : 다운로드가 가능한 타입
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName) {
		log.info("download file name : " + fileName);

		// 서버 폴더에 접근해서 해당 파일 가져오기
		Resource resource = new FileSystemResource("e:/upload/" + fileName);
		String resourceUidName = resource.getFilename();

		// 다운로드 할 때 UUID 값 제거하기
		String resourceName = resourceUidName.substring(resourceUidName.indexOf("_") + 1);

		// 헤더에 추가하기
		HttpHeaders headers = new HttpHeaders();
		try { // 한글처리를 위한 인코딩 작업
			headers.add("Content-Disposition",
					"attachment;fileName=" + new String(resourceName.getBytes("utf-8"), "iso-8859-1"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	// 이미지 파일 여부 확인
	private boolean checkImageType(File file) {
		String contentType;

		try { // 받은 파일의 형태를 알기 위함, MINE 타입에 없는 확장자 업로드 시 null이 뜸
			contentType = Files.probeContentType(file.toPath());

			// image로 시작을 하면 true를 리턴 or false를 리턴
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 폴더명 생성
	private String getFolder() {
		// 년월일 포맷을 사용하겠다고 선언
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 현재 날짜를 가져오는 객체
		Date date = new Date();

		// str에는 "2021-12-08"만 남음
		String str = sdf.format(date);

		return str.replace("-", File.separator); // 2021/12/08을 리턴
	}

}
