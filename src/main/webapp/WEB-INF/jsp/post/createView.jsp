<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="/static/css/style.css" type="text/css">

<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section class="d-flex justify-content-center">
			<div class="w-75 my-5">
				<h1>메모 입력</h1>
				<label class="mr-2">제목: </label>
				<input type="text" class="form-control col-11" id="titleInput">	
				<textarea class="form-control mt-3" rows="5" id="contentInput"></textarea>	<%-- form-control을 잡으면 col은 자동으로 잡힘 --%>
				<div class="d-flex ">
					<span class="img-icon"><i class="bi bi-image" id="imgBtn"></i></span>
					<input type="file" class="mt-3 d-none" id="fileInput">
				</div>
				<div class="d-flex justify-content-between mt-3">
					<a href="/post/list_view" class="btn btn-info">목록</a>	<%-- btn처럼 보이도록 만들어 주는 부트스트랩 --%>
					<button type="button" class="btn btn-success" id="saveBtn">저장</button>
				</div>		
			</div>
		</section>
		
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
	<script>
		$(document).ready(function() {
			
			$("#imgBtn").on("click", function() {
				// fileInput 클릭 효과
				$("#fileInput").click();
			});
			
			
			$("#saveBtn").on("click", function() {
				let title = $("#titleInput").val();
				let content = $("#contentInput").val().trim(); // textarea 사용시 깔끔하게 사용 사능(공백 제거)
				
				if(title == "") {
					alert("제목을 입력하세요");
					return;
				}
				if(content == "") {
					alert("내용을 입력하세요");
					return;
				}
				
				// 파일 유효성 검사
				if($("#fileInput")[0].files.length == 0) {
					alert("파일을 선택해주세요");
					return;
				}
				
				var formData = new FormData();
				// 파라미터, 데이터 저장
				formData.append("subject", title);
				formData.append("content", content);
				formData.append("file", $("#fileInput")[0].files[0]); // 첫 번째 파일, 1개만 업로드
				
				$.ajax({
					type: "post",
					url: "/post/create",
					data: formData,
					
					encType: "multipart/form-data",	// 파일 업로드 필수
					processData: false,
					contentType: false,
					
					success: function(data) {
						if(data.result == "success") {
							location.reload();
						} else {
							alert("글쓰기 실패");
						}
							
					}, 
					error: function() {
						alert("error occured");
					}
				});
			});
		});
	</script>
</body>
</html>