<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<img src="/images/1.png" style="border-radius: 300px; width: 20%">
	<img src="./images/2.png" style="border-radius: 300px; width: 20%">
	<div>
		<c:choose>
			<c:when test="${empty member }">
				<a href="./member/join">회원가입</a>
				<a href="./member/login">로그인</a>
			</c:when>
			<c:otherwise>
				<a href="./member/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
	</div>
	<a href="./qna/list">QNA LIST</a>
	<div>
		<img alt="" src="/file/qna/d8dc1a2a-f267-448e-9608-89e88408a76b_읭.png">
	</div>

	<button id="btn">Click</button>

	<button class="btns">Button1</button>
	<button class="btns">Button2</button>
	<button class="btns">Button3</button>
</div>
</body>
</html>