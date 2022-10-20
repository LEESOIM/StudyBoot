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
</head>
<body>
<div style="text-align: center;">
	<h1>Index page</h1>
	<img src="/images/1.png" style="border-radius: 300px; width: 20%">
	<img src="./images/2.png" style="border-radius: 300px; width: 20%">
	<div>
	<a href="./qna/list">QNA</a>
	<a href="./qna/add">Write</a>
	</div>
	
	<div>
		<img alt="" src="/file/qna/d8dc1a2a-f267-448e-9608-89e88408a76b_읭.png">
		<img alt="" src="/file/notice/f4a348a6-b367-4e8c-8961-dbb50e316490_흰둥이.PNG">
	</div>
		<a href="/fileDown/qna?fileNum=1" class="btn btn-primary">QNA DOWN</a>
		<a href="/fileDown/notice?fileNum=1" class="btn btn-primary">NOTICE DOWN</a>
</div>
</body>
</html>