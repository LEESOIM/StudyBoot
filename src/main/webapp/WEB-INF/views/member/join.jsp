<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-4">
				<h1>회원가입</h1>
				<form:form modelAttribute="memberVO" method="post"><!--action="join" 생략가능 -->

					<h3>ID</h3> <!-- path : name과 비슷한 역할 -->
					<form:input path="id" cssClass="form-control" id="id" />
					<form:errors path="id" id="idCheck"></form:errors><!-- Server에서 에러 검증 위반됐을 때 출력 -->

					<h3>PW</h3>
					<form:password path="pw" cssClass="form-control" id="pw" />
					<form:errors path="pw"></form:errors>
					<div id="pwCheck"></div>

					<h3>PW 확인</h3>
					<form:password path="pw2" cssClass="form-control" id="pw2" />
					<form:errors path="pw2"></form:errors>
					<div id="pw2Check"></div>

					<h3>Name</h3>
					<form:input path="name" cssClass="form-control" id="name" />
					<div id="nameCheck">
						${name }
						<form:errors path="name"></form:errors>
					</div>

					<h3>Age</h3>
					<form:input path="age" cssClass="form-control" id="age" />
					<form:errors path="age"></form:errors>
					
					<h3>Email</h3>
					<form:input path="email" cssClass="form-control" id="email" />
					<form:errors path="email"></form:errors>
					<div id="emailCheck"></div>
					
					<h3>Birth</h3>
					<form:input path="birth" cssClass="form-control" id="birth" />
					<form:errors path="birth"></form:errors>
					<div id="emailCheck"></div>
					

					<div>
						<button type="submit" id="joinBtn" class="btn btn-primary">회원가입</button>
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
				</form:form>
			</div>
		</div>
	</div>
</html>