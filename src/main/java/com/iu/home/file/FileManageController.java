package com.iu.home.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;
import com.iu.home.board.qna.QnaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j	
public class FileManageController {
	
	@Autowired
	QnaService qnaService;

	@GetMapping("/fileDown/{path}") //RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable(name="path") String path, QnaFileVO qnaFileVO) throws Exception {
		
		log.info("Path {} ", path);
		ModelAndView mv = new ModelAndView();
		if(path.equals("qna")) {
			qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		}
	
		//DB에서 정보 조회
		qnaFileVO.setFileName(qnaFileVO.getFileName());
		qnaFileVO.setOriName(qnaFileVO.getOriName());
		log.info("FileNum {}",qnaFileVO.getFileNum());
		log.info("FileName {}",qnaFileVO.getFileName());
		log.info("OriName {}",qnaFileVO.getOriName());
		
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path", path);
		mv.setViewName("fileManager");
		// BeanNameResolver
		// view의 이름과 일치하는 bean(객체)의 이름이 있으면 해당 bean실행
		
		// InternalResourceViewResolver
		// /WEB-INF/views/fileManager.jsp
		
		return mv;
	}
	
}
