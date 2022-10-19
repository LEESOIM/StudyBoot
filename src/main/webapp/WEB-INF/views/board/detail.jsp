<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
<div class="container-fluid">
<div class="row justify-content-center">
<div class="col-6">
	<h1>Detail Page</h1>
	
	<table class="table table-striped">
			<tr>
				<th>NUM</th>
				<th>TITLE</th>
				<th>WRITER</th>
				<th>REGDATE</th>
				<th>HIT</th>
			</tr>
			<tr>
				<td>${qnaVO.num}</td>
				<td>${qnaVO.title}</td>
				<td>${qnaVO.writer}</td>
				<td>${qnaVO.regDate}</td>
				<td>${qnaVO.hit}</td>
			</tr>
		</table>
	
	<c:forEach items="qnaVO.qnaFileVOs" var="VO">
		<img src="${VO.fileName }" style="border-radius: 300px; width: 20%">
	</c:forEach>
</div>
</div>
</div>
</body>
</html>