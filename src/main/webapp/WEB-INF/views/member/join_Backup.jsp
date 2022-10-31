<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/util.js"></script>
<script defer src="/js/memberJoin.js"></script>
</head>
<body>
	<h1>회원가입</h1>
	<form action="join" method="post" id="joinForm">

		<h3>ID</h3>
		<input type="text" name="id" id="id" placeholder="아이디 입력">
		<div id="idCheck"></div>

		<h3>PW</h3>
		<input type="password" name="pw" id="pw" placeholder="비밀번호 입력">
		<div id="pwCheck"></div>

		<h3>PW 확인</h3>
		<input type="password" id="pw2" placeholder="비밀번호 다시 입력">
		<div id="pw2Check"></div>

		<h3>Name</h3>
		<input type="text" name="name" id="name" placeholder="이름 입력">
		<div id="nameCheck"></div>

		<h3>Email</h3>
		<input type="email" name="email" id="email" placeholder="이메일 입력">
		<div id="emailCheck"></div>

		<div>
			<button id="joinBtn" class="btn btn-primary">회원가입</button>
		</div>

		<!-- 약관 test -->
		<div>
			<div>
				ALL <input type="checkbox" id="all">
			</div>
			<div>
				동의1 <input type="checkbox" class="check">
				<div>약관1</div>
			</div>
			<div>
				동의2 <input type="checkbox" class="check">
				<div>약관2</div>
			</div>
			<div>
				동의3 <input type="checkbox" class="check">
				<div>약관3</div>
			</div>
		</div>
	</form>
</html>