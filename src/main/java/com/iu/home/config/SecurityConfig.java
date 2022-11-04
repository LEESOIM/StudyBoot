package com.iu.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.iu.home.member.security.LoginFail;
import com.iu.home.member.security.LoginSuccess;

//@Configuration
@EnableWebSecurity //시큐리티를 실행하겠다
public class SecurityConfig {
	
	@Autowired
	private LoginSuccess loginSuccess;
	
	@Autowired
	private LoginFail loginFail;
	
	@Bean
	//public을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityCustomizer() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**") //index에 해당하는 이미지폴더는 보안에서 무시해라(no login)
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				.antMatchers("/result/**");
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
					.cors()
					.and()
					.csrf()
					.disable()
					.authorizeRequests()
						.antMatchers("/admin").hasRole("ADMIN") //ADMIN인 사람만 허용
						.antMatchers("/manager").hasRole("MANAGER")  //MANAGER인 사람만 허용
						.antMatchers("/qna/list").permitAll() 
						.antMatchers("/qna/**").hasRole("MANAGER")
						.anyRequest().permitAll() //그외나머지 로그인 없이 모두 접근 허용
						.and()
	  //httpSecurity.formLogin()
					.formLogin() //로그인 폼 인증 설정
						.loginPage("/member/login") //내장된 로그인 폼을 사용하지 않고 개발자가 만든 폼을 요청 URL
						.usernameParameter("id") //패스워드 파라미터는 password이지만, 개발자가 다른 파라미터 이름을 사용할때
						.passwordParameter("pw") //아이디 파라미터는 username이지만, 개발자가 다른 파라미터 이름을 사용할때
						//.defaultSuccessUrl("/") //로그인 성공할 경우 요청할 URL
						.successHandler(loginSuccess)
						//.failureUrl("/member/login?error=true&message=LoginFail") //로그인 실패할 경우 요청할 URL
						.failureHandler(loginFail)
						.permitAll()
						.and()
					.logout()
						.logoutUrl("/member/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true) //세션만료
						.deleteCookies("JSESSIONID") //쿠키지웟
						.permitAll();
		
		return httpSecurity.build();
	}
	
	//평문(Clear Text)을 암호화 시켜주는 객체 생성
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
}
