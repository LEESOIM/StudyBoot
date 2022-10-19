package com.iu.home;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;

@Controller
public class HomeController {
	
	@Value("${my.message.hi}") //properties에서 가지고 온 값을 멤버변수에 주입 
	private String message;
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class); //이 클래스 내에 로그기록을 찍을거야
	//private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaMapper qnaMapper;

	@GetMapping("/")
	public String home() throws Exception {
		log.error("Error Message");
		log.warn("Warn Message");
		log.info("Info Message");
		log.debug("Debug Message");
		log.trace("Trace Message");
		
//		List<QnaVO> ar = qnaMapper.getList();
//		log.info("List : {} size : {}", ar, ar.size()); //중괄호 안에 ar을 집어넣어라
		//System.out.println("List : "+ ar + " size : " + ar.size()); //객체를 계속 만들기 때문에 속도저하
		
		return "index";
	}
	
}
