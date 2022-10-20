package com.iu.home.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect //핵심로직을 언제(join-point) 누구에게(point-cut) 실행할지 설정	
public class Card {
	
	@Before("execution(* com.iu.home.aop.test.Transport.airPlane())")
	public void before()  {
		log.info("---------Before---------");
	}
	
	@After("execution(* com.iu.home.aop.test.Transport.get*())") //get이라고 시작하는 모든 메서드
	public void after()  {
		log.info("---------After---------");
	}
	
	@Around("execution(* com.iu.home.aop.test.Transport.take*())") 
	public Object cardTag(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("삐빅!🎫 승차 입니다");
		Object obj = joinPoint.proceed();
		log.info("삐빅!🎫 하차 입니다");
		return obj;
	}
}
