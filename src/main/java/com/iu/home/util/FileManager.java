package com.iu.home.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileManager {
	
	public String saveFile(MultipartFile multipartFile, String path) throws Exception {
		
		//1. 중복되지 않는 파일명 생성(UUID, Date)
		String fileName = UUID.randomUUID().toString();
		
		//2. 확장자
		StringBuffer bf = new StringBuffer();
		bf.append(fileName);
		bf.append("_");
		bf.append(multipartFile.getOriginalFilename());

		//3. File 저장하기
		File file = new File(path, bf.toString());
		
		// FileCopyUtils
//		FileCopyUtils.copy(multipartFile.getBytes(), file);
		// MultipartFile
		multipartFile.transferTo(file);
		
		return fileName;
	}
}
