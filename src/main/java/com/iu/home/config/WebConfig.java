package com.iu.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration//설정 파일을 만드는 객체
@Slf4j							//설정 파일을 상속해준다(***-context.xml(설정파일) 같은 역할)
public class WebConfig implements WebMvcConfigurer {

	@Value("${app.upload.base}") //SPEL
	private String filePath;
	
	@Value("${app.url.path}")
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		log.info("--------------------------------");
		log.info("filePath {} ", filePath);
		log.info("urlPath {} ", urlPath);
		log.info("--------------------------------");
		
		// /file/** 시작하는 URL요청을 Controller로 보내지 않고 /result/upload에서 반환
		// <resources mapping="/resources/**" location="/resources/" />
		registry.addResourceHandler(urlPath) //요청 URL 주소 => /file/** 온다면
				.addResourceLocations(filePath); //여기서 찾아라 => file:///result/upload/
	}
}
