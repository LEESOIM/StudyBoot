package com.iu.home.board.qna;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data //Getter+Setter
public class QnaVO {
	
	private Long num;
	@NotBlank
	private String writer;
	@NotBlank
	private String title;
	@NotBlank
	@Size(max = 10, min = 4)
	private String contents;
	private Long hit;
	private Date regDate;
	private Long ref;
	private Long step;
	private Long depth;
	
	private MultipartFile [] files; //업로드할때
	private List<QnaFileVO> qnaFileVOs; //디비 조회해서 담을때
}
