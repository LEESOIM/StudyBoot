<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<!-- jquery -->
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- include summernote css/js-->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script defer src="/js/file.js"></script>
</head>
<body>
<div class="container-fluid">
<div class="row justify-content-center">
<div class="col-6">
	<h1>Board Write Page</h1>

	<div>
		<form action="update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="num" value="${qnaVO.num }">
			<div class="mb-3">
				<label for="title" class="form-label">Title</label> 
				<input type="text" name="title" class="form-control" id="title" value="${qnaVO.title }">
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">Writer</label> 
				<input type="text" name="writer" class="form-control" id="writer" value="${qnaVO.writer }" readonly="readonly">
			</div>
			<div class="mb-3">
  				<label for="contents" class="form-label">Contents</label>
  				<textarea class="form-control" name="contents" id="contents" rows="3"></textarea>
			</div>
			
			<div class="mb-3">
				<button type="button" class="btn btn-success" id="fileAdd">FileAdd</button>
			</div>
			<div class="mb-3" id="fileBox" data-file-size="${qnaVO.qnaFileVOs.size()}">
				<c:forEach items="${qnaVO.qnaFileVOs }" var="fileVO">
					<p>${fileVO.oriName } <button type="button" class="deleteFile" data-file-num=${fileVO.fileNum }>X</button></p>
				</c:forEach>
			</div>
			

			<button class="btn btn-primary">글수정</button>
			<a href="./detail?num=${qnaVO.num}" class="btn btn-primary">취소</a>
		</form>
	</div>
	</div>
	</div>
	</div>
	
	<script>
		$("#contents").summernote({
			tabsize: 4,
			height : 300,
			minHeight : null,
			maxHeight : null,
			focus : true,
		});
		
		$('#contents').summernote('code', '${qnaVO.contents}');
	</script>
</body>
</html>