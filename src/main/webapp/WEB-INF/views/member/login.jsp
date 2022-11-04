<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login</h1>
	
	<div>
		<h3 style="color: red">${param.error }</h3>
		<h3 style="color: red">${param.message }</h3>
		<h3 style="color: red">${requestScope.msg }</h3>
	</div>
	
	<form action="login" method="post">
		<h3>ID</h3>
		<input type="text" name="id" value="${cookie.userId.value}">
		<h3>PW</h3>
		<input type="password" name="pw" value="admin">

		<div>
			<input type="checkbox" name="rememberId">
			<label>아이디 기억하기</label>
		</div>
		<button>로그인</button>
	</form>
	
	
	<!-- URL주소창에 파라미터 제거 -->
	<script type="text/javascript">
		//history.replaceState({}, null, location.pathname)
	</script>
</body>
</html>