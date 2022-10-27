package com.iu.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
}
