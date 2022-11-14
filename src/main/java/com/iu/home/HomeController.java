package com.iu.home;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.PostVO;
import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.member.MemberVO;
import com.iu.home.util.TestInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

	@Value("${my.message.hi}") // properties에서 가지고 온 값을 멤버변수에 주입
	private String message;

	@Value("${my.default}")
	private String app;

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String key;

	private final Logger log = LoggerFactory.getLogger(HomeController.class); // 이 클래스 내에 로그기록을 찍을거야
	//= private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/arrow")
	public void arrow() {
		 //Lamda식(JS Arrow Function)
		TestInterface t = (m)->System.out.println(m);
		t.info("test");
		
		TestInterface
	}

	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}

	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manager Role";
	}

	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
	
	@GetMapping("/web")
	public String webClientTest() {
		//WebClient 객체 생성
		WebClient webClient = WebClient.builder()
				 					   .baseUrl("https://jsonplaceholder.typicode.com")
				 					   .build();
		
		//결과 받음
		//toEntity() : status, headers, body을 포함하는 ResponseEntity 타입으로 받을 수 있음
//		Mono<ResponseEntity<PostVO>> res = webClient.get()
//				 									.uri("/posts/1")
//				 									.retrieve() //body를 받아 디코딩하는 간단한 메서드
//				 									.toEntity(PostVO.class);
//		PostVO postVO = res.block().getBody();
		
		
		//bodyToMono() : body의 데이터만 받고 싶을 때(결과가 0개, 1개)
//		Mono<PostVO> res = webClient.get()
//						 			.uri("/posts/2")
//						 			.retrieve()
//						 			.bodyToMono(PostVO.class);
//		PostVO postVO = res.block();
		
		
		//bodyToFlux : body의 데이터만 받고 싶을 때(결과가 0, N 개)
		Flux<PostVO> res = webClient.get()
						 			.uri("/posts")
						 			.retrieve()
						 			.bodyToFlux(PostVO.class);
		PostVO postVO = res.blockFirst();
		
		res.subscribe((s)->{
			PostVO vo = s;
			log.info("ID : {} ", s.getId());
		});
		
		log.info("Result => {}", postVO);
		return "";
	}

	@GetMapping("/address")
	@ResponseBody
	public String address() throws Exception {
		// kakao 지도 요청
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KaKaoAK " + key);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", "전북 삼성동 100");

		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<String> res = restTemplate.getForEntity("https://dapi.kakao.com/v2/local/search/address.json", String.class, req);

		return res.getBody();
	}

//	@Autowired
//	private QnaMapper qnaMapper;

	@GetMapping("/")
	public String home(HttpSession session) throws Exception {

		RestTemplate restTemplate = new RestTemplate(); // 요청을 관리

		// 1. Header
		HttpHeaders headers = new HttpHeaders();
		// header -> key:value
//		headers.add("key", "value");
//		headers.set헤더명("값");

		// 2. parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key", "value");

		// 3. 요청정보 객체 생성(1번+2번)
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		// 4. 요청 전송 후 결과 처리 메서드형식(get : URI, T.class(타입), request) (post : URI, request,T.class(타입))
//1)	//ResponseEntity<List<PostVO>> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", List.class, request);
		//PostVO postVO = response.getBody();
//2)
		List<PostVO> postVOs = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class, request);
		log.info("PostVO => {}", postVOs);

		
		
		log.error("--------------------");
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			log.info("KEY {} ", key); // SPRING_SECURITY_CONTEXT
		}

		SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		if (context != null) {
			log.info("SecurityContext {} ", context);
			log.info("SecurityContext {} ", ((MemberVO) context.getAuthentication().getPrincipal()).getId());
		}

		log.error("message {} ", message);
		log.error("default {} ", app);
		log.error("--------------------");

//		log.error("Error Message");
//		log.warn("Warn Message");
//		log.info("Info Message");
//		log.debug("Debug Message");
//		log.trace("Trace Message");

//		List<QnaVO> ar = qnaMapper.getList();
//		log.info("List : {} size : {}", ar, ar.size()); //중괄호 안에 ar을 집어넣어라
		// System.out.println("List : "+ ar + " size : " + ar.size()); //객체를 계속 만들기 때문에
		// 속도저하 -> append시켜주기

		return "index";
	}

}
