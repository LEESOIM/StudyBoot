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
</head>

<body>
	<h1>Board Write Page</h1>

	<div>
		<form action="add" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="title" class="form-label">Title</label> 
				<input type="text" name="title" class="form-control" id="title">
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">Writer</label> 
				<input type="text" name="writer" class="form-control" id="writer">
			</div>
			<div class="mb-3">
  				<label for="contents" class="form-label">Contents</label>
  				<textarea class="form-control" name="contents" id="contents" rows="3"></textarea>
			</div>
			<div class="mb-3">
				<label for="files" class="form-label">File1</label> 
				<input type="file" name="files" class="form-control" id="files">
			</div>
			<div class="mb-3">
				<label for="files" class="form-label">File2</label> 
				<input type="file" name="files" class="form-control" id="files">
			</div>

			<button class="btn btn-primary" href="./add" type="submit">글쓰기 Button</button>
		</form>
	</div>
	
	<script type="text/javascript">
		$("#contents").summernote({
			height : 400,
			minHeight : null,
			maxHeight : null,
			focus : true,
		});
	</script>
</body>
</html>