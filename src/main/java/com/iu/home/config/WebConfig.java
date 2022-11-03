package com.iu.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration //객체 선언후 자동 주입(***-context.xml(설정파일) 같은 역할)
@Slf4j
public class WebConfig implements WebMvcConfigurer {

	@Value("${app.upload.base}") //sqEl
	private String filePath;
	
	@Value("${app.url.path}")
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("--------------------------------");
		log.info("filePath {} ", filePath);
		log.info("urlPath {} ", urlPath);
		log.info("--------------------------------");
		
		//<resources mapping="/resources/**" location="/resources/" />
		registry.addResourceHandler(urlPath) //요청 URL 주소 -> /file/** 온다면
				.addResourceLocations(filePath); //여기서 찾아라 -> file:///result/upload/
	}
}
