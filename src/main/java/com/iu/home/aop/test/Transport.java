package com.iu.home.aop.test;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Transport {

	public void takeBus() {
		log.info("λ²μ€-----ππππ");
	}
	
	public void takeSubway() {
		log.info("μμ² -----ππππ");
	}

	public void getTaxi() {
		log.info("νμ-----ππππ");
	}
	
	public void airPlane() {
		log.info("λ±κΈ°-----βπ«π¬β");
	}
}