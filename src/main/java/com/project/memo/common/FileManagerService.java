package com.project.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	// 여러 메소드에서 사용할 수 있도록
	// 특수한 이유로 public
	// 더 이상 수정이 불가능 하도록 final, 보통 변수명을 대문자로 사용
	public final String FILE_UPLOAD_PATH = "D:\\임승화\\project\\img/";
	

	// 파일 저장
	public String saveFile(int userId, MultipartFile file) {	
		
		/*
		 * userId를 같이 받는 이유? -> 파일명이 겹칠 경우를 대비해서 
		 * 사용자 별로 구분할 수 있도록
		 * 사용자가 파일을 올린 시간으로 구분
		 *  /12_1232345346/test.png = userId_createdAt/file
		 * 
		 * 파일 경로 만들기 = 문자열
		 * 1970년 1월 1일 기준으로 현재 밀리 세컨이 경과 되었는지 나타내는 수
		 */
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		// 실제 파일 경로
		String filePath = FILE_UPLOAD_PATH + directoryName; 
		
		// 디렉터리 생성 -> 디렉터리는 파일이다 !
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 디렉터리 생성 에러
			return null;
		}
		
		// 저장 -> 실제 파일 데이터를 얻어와 바이츠로 저장
		try {
			byte[] bytes = file.getBytes();
			// 파일 저장 경로 + 파일 이름 경로 객체
			Path path = Paths.get(filePath + file.getOriginalFilename());
			// 파일 저장
			Files.write(path, bytes);
		} catch (IOException e) {
			// 저장 실패
			e.printStackTrace();
			return null;
		}	 
		
		// 파일 접근 가능한 주소 리턴
		// <img src="/images/12_23423524623a/test.png">
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	

}
