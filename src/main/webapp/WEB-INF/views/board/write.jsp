<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<!-- jquery -->
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- include summernote css/js-->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script defer src="/js/file.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-6">
				<h1>Board Write Page</h1>

				<div>
					<form:form modelAttribute="qnaVO" method="post">

						<input type="hidden">
						<sec:csrfInput/>
					
						<div class="mb-3">
							<label for="title" class="form-label">Title</label>
							<form:input path="title" cssClass="form-control" id="title" />
							<form:errors path="title"></form:errors>
						</div>
						<div class="mb-3">
							<label for="writer" class="form-label">Writer</label>
							<form:input path="writer" cssClass="form-control" id="writer" />
							<form:errors path="writer"></form:errors>
						</div>
						<div class="mb-3">
							<label for="contents" class="form-label">Contents</label>
							<form:textarea path="contents" cssClass="form-control"
								id="contents" />
							<form:errors path="contents"></form:errors>
						</div>

						<div class="mb-3">
							<button type="button" class="btn btn-success" id="fileAdd">FileAdd</button>
						</div>
						<div class="mb-3" id="fileBox"></div>

						<button type="submit" class="btn btn-primary">글쓰기</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$("#contents").summernote({
			tabsize : 4,
			height : 300,
			minHeight : null,
			maxHeight : null,
			focus : true,
			callbacks: {
				onImageUpload: function(file) {
					console.log("Image Upload")
					
					//ajax file server로 upload 후 경로를 받아서 사용
					uploadFile(file);
				},
				
				onMediaDelete:function(file){
					console.log("Delete Media")
					console.log("DeleteFile => ", file)
					deleteFile(file);
				}
			}
		});
		
		//ajax upload 함수
		function uploadFile(file) {
			console.log("file ",file);
			console.log("fileName => ", file[0].name);
			
			//<form>태그를 준비
			const formData = new FormData();
			
			//<input type="file">
			formData.append('file', file[0]);
			
			$.ajax({
				type:"POST",
				url:"summerFile",
				data:formData,
				cache:false,
				contentType:false,
				processData:false,
				enctype:'multipart/form-data',
				success:function(img){ //성공했을때 실행되는 함수
					console.log("Image => ", img);
				
					//img = '<img src="'+img+'">';
					//$("#contents").summernote('pasteHTML', img);
					$("#contents").summernote('insertImage', img, 'file[0].name');
				},
				error:function(){ //에러가 발생했을때 실행되는 함수
					console.log("이미지 업로드 실패");
				}
			})
		}
		
		
		function deleteFile(file) {
			console.log("src => ", file.attr("src"));
			$.post("./summerFileDelete", {fileName:file.attr("src")}, function(result){
				console.log("result =>", result);
			})
		}
		
	</script>
</body>
</html>