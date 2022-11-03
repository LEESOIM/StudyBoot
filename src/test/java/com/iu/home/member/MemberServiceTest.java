package com.iu.home.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class MemberServiceTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("admin");
		memberVO.setPw(passwordEncoder.encode("admin"));
		memberVO.setName("어드민");
		memberVO.setEmail("admin@naver.com");
		int result = memberMapper.setJoin(memberVO);
		assertEquals(1, result);
	}
}
