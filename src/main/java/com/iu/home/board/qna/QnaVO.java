package com.iu.home.board.qna;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data //Getter+Setter
public class QnaVO {
	
	private Long num;
	private String writer;
	private String title;
	private String contents;
	private Long hit;
	private Date regDate;
	private Long ref;
	private Long step;
	private Long depth;
	
	private MultipartFile [] files;
	private List<QnaFileVO> qnaFileVOs;
}
