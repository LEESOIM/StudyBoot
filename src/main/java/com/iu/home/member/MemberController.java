package com.iu.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("/member/*")
@Controller
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("join")		//비어있는 memberVO 객체를 model에 담아서 jsp로 보내라!
	public void setJoin(@ModelAttribute MemberVO memberVO) throws Exception {
		//MemberVO memberVO = new MemberVO();
		//model.addAttribute("memberVO", memberVO); 
	}
	
	@PostMapping("join") 	//mv를 호출할때 memberVO를 검증해서 에러가 있거나 실패하면 그 결과를 bindingResult 객체에 그 결과를 담아라!
	public ModelAndView setJoin(ModelAndView mv, @Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
//		if(bindingResult.hasErrors()) {
//			//검증에 실패하면 회원가입 jsp로 foward
//			log.info("===검증 에러 발생===");
//			mv.setViewName("member/join");
//			return mv;
//		} 

		
		//check==false : 검증성공(error 없음)
		//check==true : 검증실패(error 있음)
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		if(check) {
			log.info("===검증 에러 발생===");
			mv.setViewName("member/join");
			//--------------------------------------
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for(FieldError fileError:errors) {
				log.info("FiledError => {}", fileError);
				log.info("Filed => {}", fileError.getField());
				log.info("Message => {}", fileError.getRejectedValue());
				log.info("Default => {}", fileError.getDefaultMessage());
				log.info("Code => {}", fileError.getCode());
				mv.addObject(fileError.getField(), fileError.getDefaultMessage());
				log.info("---------------------------");
			}
			return mv;
		}
		
//		int result = memberService.setJoin(memberVO);
		mv.setViewName("redirect:./login");
		return mv;
	}
	
	
	@GetMapping("idCheck")
	@ResponseBody
	public Long getIdCheck(MemberVO memberVO) throws Exception {
		return memberService.getIdCheck(memberVO);
	}
	
	
	
	@GetMapping("login") //파라미터 값이 넘어오면 담고 안넘어오면 안담아도돼
	public String getLogin(@RequestParam(defaultValue = "false", required = false)boolean error, String message, Model model) throws Exception {
		if(error) {
			model.addAttribute("msg", "ID 또는 PW를 확인하세요");
		}
		//Controller에서 받아서 jsp로 다시 보내도 됨
		return "member/login";
	}

	@PostMapping("login")
	public String getLogin() throws Exception {
		log.info("==========Login Post==========");
		return "member/login";
	}
	
//Spring Security를 사용하면 Controller를 안거치고 MemberSecurityService로 보내줌
//	@PostMapping("login")
//	public String getLogin(MemberVO memberVO, HttpSession session) throws Exception {
//		memberVO = memberService.getLogin(memberVO);
//		session.setAttribute("member", memberVO);
//		return "member/login";
//	}
	
//	@GetMapping("logout")
//	public String getLogout(HttpSession session) throws Exception {
//		session.invalidate(); //세션만료
//		return "redirect:/";
//	}
	
	@GetMapping("myPage")
	public String getMyPage() throws Exception {
		return "member/myPage";
	}
	
}
