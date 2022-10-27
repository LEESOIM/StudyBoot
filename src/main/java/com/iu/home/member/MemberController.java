package com.iu.home.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/member/*")
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("join")
	public void setJoin() throws Exception {

	}
	
	@PostMapping("join")
	public String setJoin(MemberVO memberVO) throws Exception {
		int result = memberService.setJoin(memberVO);
		if(result==1) {
			return "redirect:./login";
		}
		return "member/join";
	}
	
	
	@GetMapping("idCheck")
	@ResponseBody
	public Long getIdCheck(MemberVO memberVO) throws Exception {
		return memberService.getIdCheck(memberVO);
	}
	
	
	
	@GetMapping("login")
	public String getLogin() throws Exception {
		return "member/login";
	}
	
	@PostMapping("login")
	public String getLogin(MemberVO memberVO, HttpSession session) throws Exception {
		memberVO = memberService.getLogin(memberVO);
		session.setAttribute("member", memberVO);
		if(memberVO!=null) {
			return "redirect:/";
		}
		return "member/login";
	}
	
	@GetMapping("logout")
	public String getLogout(HttpSession session) throws Exception {
		session.invalidate(); //세션만료
		return "redirect:/";
	}
	
}
