package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.iu.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutSuccessCustom implements LogoutSuccessHandler {

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String key;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		log.info("=== logout 성공시에만 실행 ===");

		MemberVO memberVO = (MemberVO) authentication.getPrincipal(); // memberVO
		String social = memberVO.getSocial();

//		if(social!=null && social.equals("kakao")) {
//			
//		} else if(social!=null && social.equals("google")){
//			
//		}

		if (social != null) {
			if (social.equals("kakao")) {

				// https://developers.kakao.com/logout
				// response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+key+"&logout_redirect_uri=http://localhost:81/member/logoutResult");
				RestTemplate restTemplate = new RestTemplate();
				// header X
				// parameter X
				ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://developers.kakao.com/logout", null, String.class);
				log.info("ResponseEntity => {}", responseEntity);
				
			} else if (social.equals("google")) {

			}
		} else {
			response.sendRedirect("/");
		}
	}
}