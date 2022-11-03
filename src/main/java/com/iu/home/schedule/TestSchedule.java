package com.iu.home.schedule;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.home.member.MemberMapper;
import com.iu.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestSchedule {
	
	@Autowired
	private MemberMapper memberMapper;
	
	//@Scheduled(cron = "30 * * * * *")
	public void cron() throws Exception {
		log.info("Cron 매초 실행");
		log.info(Calendar.getInstance().getTime().toString());
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("AutoId"+Calendar.getInstance().getTimeInMillis());
		memberVO.setPw("1234");
		memberVO.setName("name");
		memberVO.setEmail("Email");
		log.info("Result {}", memberMapper.setJoin(memberVO));
		
	}
		
				//고정 간격으로 반복 실행 //서버가 실행되고 1초후 이 메서드 실행해라
	//@Scheduled(fixedRate = 3000, initialDelayString = "1000")
	public void ts1() {
		log.info("Schedule 실행");
	}
}
