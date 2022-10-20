package com.iu.home.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j		//CustomView의 bean name
public class FileManager extends AbstractView {
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		QnaFileVO qnaFileVO = (QnaFileVO)model.get("fileVO");
		String path = (String)model.get("path");
		log.info(";;;;;;;;;;;;;;;;;;;;;;;;;;;;");
		log.info("FileVO {} ", qnaFileVO);
		
		File file = new File("D:/result/upload/"+path, qnaFileVO.getFileName());
		
		//한글 처리
		response.setCharacterEncoding("UTF-8");
		
		//총 파일의 크기
		response.setContentLengthLong(file.length());
		
		//다운로드시 파일 이름 인코딩
		String oriName = URLEncoder.encode(qnaFileVO.getOriName(), "UTF-8");
		
		//header 설정(파일의 부가적인 정보를 내보낸다)
		response.setHeader("Content-Disposition", "attachment;filename=\""+oriName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary"); //텍스트가 아니라 이진파일
		
		//HDD에서 파일을 읽고
		FileInputStream fi = new FileInputStream(file); //어느폴더에 어느파일을 읽어올거냐
		
		//Client로 전송 준비
		OutputStream os = response.getOutputStream();
		
		//전송
		FileCopyUtils.copy(fi, os);
		
		//자원해제
		os.close();
		fi.close();
	}
	
	
	//파일저장
	public String saveFile(MultipartFile multipartFile, String path) throws Exception {
		
		//1. 중복되지 않는 파일명 생성(UUID, Date)
		String fileName = UUID.randomUUID().toString();
		
		//2. 확장자
		StringBuffer bf = new StringBuffer();
		bf.append(fileName);
		bf.append("_");
		bf.append(multipartFile.getOriginalFilename());
		
		//파일명과 확장자 분리(한글파일명 깨질때)
//		String ex = multipartFile.getOriginalFilename();//8cc0ae7_다이어그램.zip
//		ex = ex.substring(ex.lastIndexOf("."));
//		bf.append(ex); 									//8cc0ae7_.zip
		
		fileName = bf.toString();

		//3. File 저장하기
		File file = new File(path, bf.toString());
		
		// FileCopyUtils
//		FileCopyUtils.copy(multipartFile.getBytes(), file);
		// MultipartFile
		multipartFile.transferTo(file);
		
		return fileName;
	}
}
