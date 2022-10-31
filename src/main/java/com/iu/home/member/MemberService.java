package com.iu.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	public int setJoin(MemberVO memberVO) throws Exception {
		int result = memberMapper.setJoin(memberVO);
		if(result<1) {
			throw new Exception(); //둘 중 하나라도 문제가 생기면 강제로 롤백
		}
		
		result = memberMapper.setMemberRole(memberVO);
		if(result<1) {
			throw new Exception(); 
		}
		
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberMapper.getLogin(memberVO);
	}
	
	public Long getIdCheck(MemberVO memberVO) throws Exception{
		return memberMapper.getIdCheck(memberVO);
	}
	
	
	//사용자 정의 검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean check=false;
		//check==false : 검증성공(error 없음)
		//check==true : 검증실패(error 있음)
		
		//1. Annotation 검증
		check = bindingResult.hasErrors();
		
		//2. Password 두개가 일치하는지 검증
		if(!memberVO.getPw().equals(memberVO.getPw2())) {
			check=true;
			
			//에러메세지
			//bindingResult.rejectValue("멤버변수명(path)", "properties의 key(코드)");
			bindingResult.rejectValue("pw2", "member.password.notEqual");
		}
		
		//3. ID 중복 검증
		if(0<memberMapper.getIdCheck(memberVO)) {
			check=true;
			bindingResult.rejectValue("id", "member.id.useId");
		}
		
		return check;
	}
}
