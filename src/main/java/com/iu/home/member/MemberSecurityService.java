package com.iu.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSecurityService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("===========로그인 시도중===========");
		//1)MemberVO로 보낸 다음 String으로 받아오기
//		MemberVO memberVO = new MemberVO();
//		memberVO.setId(username);
//		memberMapper.getLogin(memberVO);
		
		//2)login Mapper 수정
		MemberVO memberVO = memberMapper.getLogin(username);
	 	log.info("MemberVO : {}", memberVO);
		return memberVO;
	}
}
