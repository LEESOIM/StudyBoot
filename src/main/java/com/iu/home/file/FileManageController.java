package com.iu.home.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j	
public class FileManageController {

	@GetMapping("/fileDown/{path}") //RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable String path, QnaFileVO qnaFileVO) throws Exception {
		
		log.info("Path {} ", path);
		
		ModelAndView mv = new ModelAndView();
	
		//DB에서 정보 조회
		qnaFileVO.setFileName("d8dc1a2a-f267-448e-9608-89e88408a76b_읭.png");
		qnaFileVO.setOriName("읭.png");
//		qnaFileVO.setFileName(qnaFileVO.getFileName());
//		qnaFileVO.setOriName(qnaFileVO.getOriName());
		log.info("FileNum {}",qnaFileVO.getFileNum());
		log.info("FileName {}",qnaFileVO.getFileName());
		log.info("OriName {}",qnaFileVO.getOriName());
		
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path", path);
		mv.setViewName("fileManager");  //CustomView의 bean name으로 설정
		
		return mv;
	}
	
}
