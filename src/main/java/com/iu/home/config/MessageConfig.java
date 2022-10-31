package com.iu.home.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

	@Bean //라이브러리로 받은 클래스의 객체를 만들때 사용!
	public LocaleResolver locale() {
		//(1) 둘 중 뭘 쓸지 결정하기
		//1.Session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		//2.Cookie
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN); //기본위치
		cResolver.setCookieName("lang");
		return cResolver;
	}
	
	
	//(2) Message Interceptor 객체 생성
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		//parameter를 받아서 언어 구분
		//url?lang=ko
		return localeChangeInterceptor;
	}
	
	
	
	
	// ***-context.xml
	// <bean class="" id=""> == new 생성자()
	// @Bean("my")
	// public String getString() {
	//	return new String();
	// }

}
