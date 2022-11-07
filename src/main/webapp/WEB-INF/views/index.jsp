<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel="stylesheet">
<script defer src="/js/test.js"></script>
</head>
<body>
	<div style="text-align: center;">
		<h1>Index page</h1>
		<h1>
			<spring:message code="hi" var="h"></spring:message>
		</h1>
		<h1>
			<spring:message code="test" text="code가 없을 때 출력하는 메세지"></spring:message>
		</h1>
		<img src="/images/1.png" style="border-radius: 300px; width: 20%">
		<img src="./images/2.png" style="border-radius: 300px; width: 20%">
		
		<div>
			<!-- 로그인 성공 -->
			<sec:authorize access="isAuthenticated()"> <!-- 인증이 되었으면 -->
			<sec:authentication property="Principal" var="member"/>
				<h3><spring:message code="welcome" arguments="${member.name}"></spring:message> [ID : ${member.id}]</h3>
				<h3><spring:message code="welcome2" arguments="${member.id}@${member.name}" argumentSeparator="@"></spring:message></h3>
				
				<a href="./member/myPage">마이페이지</a>
				<a href="#" id="logout">로그아웃</a>
				<form action="./member/logout" method="post" id="logoutForm">
					<sec:csrfInput/>
					<button>로그아웃</button>
				</form>
			</sec:authorize>
			
			<!-- 로그인 전 -->
			<sec:authorize access="!isAuthenticated()"> <!-- 인증이 안 되었으면 -->
				<a href="./member/join">회원가입</a>
				<a href="./member/login">로그인</a>
				<a href="/oauth2/authorization/kakao" class="btn btn-warning">KaKao</a>
			</sec:authorize>
			 
			<sec:authorize url="/admin"> <!-- config에 등록된 url의 권한 -->
				<a href="./admin">관리자</a>
			</sec:authorize>
			
			<sec:authorize access="hasAnyRole('ADMIN','MANAGER')">
				<a href="./manager">매니저</a>
			</sec:authorize>
			
		<a href="./qna/list">LIST</a>
		</div>
		
		<button id="btn">Click</button>

		<button class="btns">Button1</button>
		<button class="btns">Button2</button>
		<button class="btns">Button3</button>
		<h1>${h }</h1>
		<h1>${h }</h1>
		<h1>${h }</h1>
		<script type="text/javascript">
			$('#logout').click(function(){
				$("#logoutForm").submit();
			})
		</script>
	</div>
</body>
</html>