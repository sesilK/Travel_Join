<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- include summernote css -->
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
</head>
<body>
	<h1>reviewWrite</h1>

	<form action="reviewWrite" method="POST" name="reviewForm"
		onsubmit="return checkForm();" enctype="multipart/form-data">
		여행 <select name="planId">
			<option selected value="">선택해주세요.</option>
			<option value="여행1">여행1</option>
			<option value="여행2">여행2</option>
			<option value="여행3">여행3</option>
		</select><br /> 별점 <select name="stars">
			<option selected value="">선택해주세요.</option>
			<c:forEach var="i" begin="0" end="10" step="1">
				<option value="${10/2.0 - i/2.0}">${10/2.0 - i/2.0}</option>
			</c:forEach>
		</select><br /> 제목 <input type="text" name="title" /><br /> 내용
		<textarea id="summernote" name="content"></textarea>
		<br />
		<button type="submit">등록하기</button>
		<a href="reviewBbs"><button type="button">돌아가기</button></a>
	</form>


	<!-- include jquery, summernote js -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/summernote/summernote-lite.js"></script>
	<script src="/js/summernote/lang/summernote-ko-KR.js"></script>
	<script>
	
		$(document).ready(function() {			
			
			$('#summernote').summernote({
				width : 800, // set editor width
				height : 400, // set editor height
				minHeight : null, // set minimum height of editor
				maxHeight : null, // set maximum height of editor
				focus : true, // focus  여부 설정 (false -> focus)
				lang : 'ko-KR', // 기본 메뉴언어 US->KR로 변경
				placeholder : '', //placeholder 설정
				callbacks: {
				    onImageUpload: function(files, editor, welEditable) { //이미지 첨부
				        for (let i = files.length - 1; i >= 0; i--) { // 다중 업로드
				            uploadSummernoteImageFile(files[i], this);
				        }
				    },
				    onMediaDelete: function ($target, editor, $editable) { //이미지 삭제
	                	let imageName = $target.attr('src').split('/').pop()
	                	deleteSummernoteImageFile(imageName)
	                }
				}
			});
			
		});
		
		
		//파일 업로드 함수
		function uploadSummernoteImageFile(file, el) {
			let formData = new FormData();
			formData.append("file", file);
			$.ajax({
				data : formData,
				type : "POST",
				url : "/uploadSummernoteImageFile",	//Controller 요청 주소
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : (data)=> {
					$(el).summernote('editor.insertImage', data.url);
				}
			});
		}

		//파일 삭제 함수
		function deleteSummernoteImageFile(imageName) {
			let formData = new FormData()
			formData.append('file', imageName)
	        $.ajax({
	            data: formData,
	            type: 'POST',
	            url: "/deleteSummernoteImageFile",
	            contentType: false,
	            enctype: 'multipart/form-data',
	            processData: false,
	        })
	    }
		
		
		
		//등록버튼 클릭시 실행 조건 확인
		function checkForm() {
			let planId = $('select[name="planId"]').val();
			let stars = $('select[name="stars"]').val();
			let title = $('input[name="title"]').val();
			let content = $('textarea[name="content"]').val();
			if (planId === "") {
				alert("여행을 선택해주세요.");
				return false;
			} else if (stars === "") {
				alert("별점을 선택해주세요.");
				return false;
			} else if (title === "") {
				alert("제목을 입력해주세요.");
				return false;
			} else if (content === "") {
				alert("내용을 입력해주세요.");
				return false;
			} else {
				return true;
			}
		};
		
		
	</script>
</body>
</html>