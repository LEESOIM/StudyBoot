package com.iu.home.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class MemberVO implements UserDetails, OAuth2User {
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
	@Range(max = 150, min = 0)
	private int age;
	@Past
	private Date birth;
	
	private boolean enabled;
	private List<RoleVO> roleVOs;
	
	
	
	//====================Social Login====================
	//KaKao, Naver, Google
	private String social;
	
	//OAuth2User, Token 등 정보 저장
	private Map<String, Object> attributes;
	
	

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}
	
	@Override		//? : GrantedAuthority 타입이거나 GrantedAuthority를 상속받은 것들
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		return authorities;
	}
	
	@Override
	public String getPassword() {
		//PW 반환
		return this.getPw();
	}
	
	@Override
	public String getUsername() {
		//ID 반환
		return this.id;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// 계정의 만료 여부
		// true : 만료 안됨
		// false : 만료 됨, 로그인 불가
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// 계정의 잠김 여부
		// true : 잠기지 않음
		// false : 잠김, 로그인 불가
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 만료 여부
		// true : 만료 안됨
		// false : 만료 됨, 로그인 불가
		return true;
	}
	
	public boolean isEnabled() {
		// isEnabled
		// 계정 사용가능 여부
		// true : 계정 활성화(사용가능)
		// false : 계정 비활성화(사용불가능) 
		return true;
	}
	
}
