package com.iu.home.member;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class MemberVO {
	@NotBlank(message = "ID 필수~!") //Null 허용하지 않음
	private String id;
	@NotBlank
	@Size(max = 8, min = 4) 
	private String pw;
	private String pw2;
	@NotBlank
	private String name;
	@NotBlank
	@Email //이메일형식
	private String email;
	private boolean enabled;
	
	private List<RoleVO> roleVOs;
	@Range(max = 150, min = 0)
	private int age;
	@Past
	private Date birth;
}
