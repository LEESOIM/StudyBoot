<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div class="col-8">
<h1>List Page</h1>
	<table class="table table-hover">
		<thead>
			<tr>
			 	<th>NUM</th>
			 	<th>WRITER</th>
			 	<th>TITLE</th>
			 	<th>HIT</th>
			 	<th>REGDATE</th>
			 </tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vo">
				<tr>
					<td>${vo.num}</td>
					<td>${vo.writer}</td>
					<td><a href="./detail?num=${vo.num }">${vo.title}</a></td>
					<td>${vo.hit}</td>
					<td>${vo.regDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div>
		<a href="./add" class="btn btn-primary">글쓰기</a>
	</div>
	</div>
	</div>
	</div>
	
	
	<script type="text/javascript">
		let result = '${param.result}';
		console.log("result",result)
		if(reulst != "") {
			if(reulst == 1) {
				alert('등록성공');
			}else {
				alert('등록실패');
			}
		}
	</script>
</body>
</html>