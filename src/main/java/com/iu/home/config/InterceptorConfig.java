package com.iu.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.iu.home.interceptors.StudyInterceptor;
import com.iu.home.interceptors.TestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired //IOC(Inversion of Control)
	private TestInterceptor testInterceptor;
	
	@Autowired
	private StudyInterceptor studyInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//적용할 Interceptor 등록
		//method 체이닝(사슬처럼 연결)
		registry.addInterceptor(testInterceptor)
		
				//Interceptor를 적용할 URL 등록
				.addPathPatterns("/qna/**") 
				.addPathPatterns("/notice/**")
				
				//제외할 URL등록
				.excludePathPatterns("/qna/detail")
				.excludePathPatterns("/qna/add");
		
		//추가 Interceptor
		registry.addInterceptor(studyInterceptor)
				.addPathPatterns("/qna/**");
		
		
		
		
		//Interceptor 순서
		//Config class에 등록된 순서대로 적용(나갈때는 역방향)
	}
}
