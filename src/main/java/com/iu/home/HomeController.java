package com.iu.home;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.member.MemberVO;

@Controller
public class HomeController {
	
	@Value("${my.message.hi}") //properties에서 가지고 온 값을 멤버변수에 주입 
	private String message;
	
	@Value("${my.default}") 
	private String app;
	
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class); //이 클래스 내에 로그기록을 찍을거야
	//private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manager Role";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
	
	
//	@Autowired
//	private QnaMapper qnaMapper;

	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		log.error("--------------------");
		Enumeration<String> enumeration = session.getAttributeNames(); //
		while(enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			log.info("KEY {} ", key); //SPRING_SECURITY_CONTEXT
		}
		
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(context != null) {
			log.info("SecurityContext {} ", context);
			log.info("SecurityContext {} ", ((MemberVO)context.getAuthentication().getPrincipal()).getId());
		}
		
		log.error("message {} ", message);
		log.error("default {} ", app);
		log.error("--------------------");
		
		
//		log.error("Error Message");
//		log.warn("Warn Message");
//		log.info("Info Message");
//		log.debug("Debug Message");
//		log.trace("Trace Message");
		
//		List<QnaVO> ar = qnaMapper.getList();
//		log.info("List : {} size : {}", ar, ar.size()); //중괄호 안에 ar을 집어넣어라
		//System.out.println("List : "+ ar + " size : " + ar.size()); //객체를 계속 만들기 때문에 속도저하 -> append시켜주기
		
		return "index";
	}
	
}
