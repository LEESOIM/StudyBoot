package com.iu.home.member;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("===== Social 로그인 시도 =====");
		log.info("UserRequest {}", userRequest);
		log.info("AccessToken {}", userRequest.getAccessToken());
		log.info("ClientRegistration {}", userRequest.getClientRegistration());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("========== 사용자 정보 ==========");
		log.info("Name => {}", oAuth2User.getName()); //사용자의 고유 아이디
		log.info("Attributes => {}", oAuth2User.getAttributes());
		log.info("Authorities => {}", oAuth2User.getAuthorities());
		
		return null;
	}
	
	private OAuth2User socialJoinCheck(OAuth2UserRequest oAuth2UserRequest) {
		//회원가입 유무
	}
}
