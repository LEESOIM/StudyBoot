package com.iu.home.aop.test;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Transport {

	public void takeBus() {
		log.info("버스-----🚎🚎🚎🚎");
	}
	
	public void takeSubway() {
		log.info("쟈철-----🚋🚋🚋🚋");
	}

	public void getTaxi() {
		log.info("택시-----🚖🚖🚖🚖");
	}
	
	public void airPlane() {
		log.info("뱅기-----✈🛫🛬✈");
	}
}