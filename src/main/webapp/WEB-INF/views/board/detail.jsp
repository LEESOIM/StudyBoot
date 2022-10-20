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
				<textarea class="form-control" name="contents" rows="10" readonly="readonly">${qnaVO.contents }</textarea>

				<div class="d-flex">
					<c:forEach items="${qnaVO.qnaFileVOs}" var="VOs">
						<div>
							<img src="/file/qna/${VOs.fileName }">
							<a href="/fileDown/qna?fileNum=${VOs.fileNum }" class="btn btn-primary">Down</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>