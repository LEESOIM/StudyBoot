package com.iu.home.board.qna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.iu.home.util.Pager;

@SpringBootTest
@Transactional
public class QnaMapperTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QnaMapper qnaMapper;

	// @BeforeAll
	static void beforClass() {
		System.out.println("전체 Test 실행전 !!!");
	}

	// @BeforeEach
	void beforeEach() {
		System.out.println("Test 메서드 실행전");

	}

	// @Test
	void test() throws Exception {
		log.info("test!!!");
	}

	// @AfterEach
	void afterEach() {
		System.out.println("Test 메서드 실행후");

	}

	// @AfterAll
	static void afterAll() {
		System.out.println("전체 Test 실행후 !!!");
	}

	// @Test
	void test2() {
		// qnaMapper.setUpdate(qnaVO);
		// qnaMapper.setDelete(qnaVO);
		log.info("test2!!!");
	}

	// @Test
	void getList(Pager page) throws Exception {
		List<QnaVO> ar = qnaMapper.getList(page);
		log.info("List : {}", ar);
		assertNotEquals(0, ar.size());
	}

	
	@Rollback(false) //rollback을 하지 않는 메서드가 있을때
	//@Test
	void setAdd() throws Exception {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setWriter("WITER ");
		qnaVO.setTitle("TITLE ");
		qnaVO.setContents("CONTENTS ");
		int result = qnaMapper.setAdd(qnaVO);
		log.info("List : {}", qnaVO);
		assertEquals(1, result);
	}
}
