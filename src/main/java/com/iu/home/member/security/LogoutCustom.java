package com.iu.home.member.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCustom implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		log.info("========== LogoutHandler ===========");
		// 1. 일반 로그인인지 ? social 로그인인지 ?
		log.info("Authentication => {}", authentication);
		log.info("누가 ?? => {}", authentication.getPrincipal().toString());

		request.getSession().invalidate();
	}

}