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
	<h1>Index page</h1>
	<img src="/images/1.png" style="border-radius: 300px; width: 20%">
	<img src="./images/2.png" style="border-radius: 300px; width: 20%">
	<a href="./qna/list">QNA</a>
	<a href="./qna/add">Write</a>
</body>
</html>