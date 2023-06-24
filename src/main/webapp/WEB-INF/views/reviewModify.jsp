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
	<h1>reviewModify</h1>

		여행 <select name="planId">
			<option selected value="">선택해주세요.</option>
			<option value="여행1">여행1</option>
			<option value="여행2">여행2</option>
			<option value="여행3">여행3</option>
		</select><br />
		별점 <select name="stars">
			<c:forEach var="i" begin="0" end="10" step="1">
				<option value="${10/2.0 - i/2.0}">${10/2.0 - i/2.0}</option>
			</c:forEach>
		</select><br />
		<input type="text" name="title" placeholder="제목을 입력해주세요"/><br />
		<textarea id="summernote" name="content"></textarea>
		<br />
		<button type="submit" id="submitBtn">수정하기</button>
		<a href="javascript:history.back()"><button type="button">돌아가기</button></a>
		
		
		
	<!-- include jquery, summernote js -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="/js/summernote/summernote-lite.js"></script>
	<script src="/js/summernote/lang/summernote-ko-KR.js"></script>
	<script>
	
		$(document).ready(function() {
			
			//썸머노트 불러오기
			$('#summernote').summernote({
				width : 800, // set editor width
				height : 400, // set editor height
				minHeight : null, // set minimum height of editor
				maxHeight : null, // set maximum height of editor
				focus : false, // focus  여부 설정 (false -> focus)
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
			
			//내용 불러오기
			let planId = '${item.planId}';
			let stars = '${item.stars}';
			let title = '${item.title}';
			let content = '${item.content}';
			$('select[name="planId"]').val(planId);
			$('select[name="stars"]').val(stars);
			$('input[name="title"]').val(title);
			$('#summernote').summernote('code', content);
			/* $('div[role="textbox"]').append(content); */ // <- <p><br></p> 삽입되는 문제
			
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
	
		document.getElementById("submitBtn").addEventListener("click", function() { //수정버튼 클릭시
			
			let reviewId = '${item.reviewId}';
			let userId = '${item.userId}';
			let planId = $('select[name="planId"]').val();
			let stars = $('select[name="stars"]').val();
			let title = $('input[name="title"]').val();
			let content = $('div[role="textbox"]')[0].innerHTML;
			
			if (planId === "") {
				alert("여행을 선택해주세요.");
				return false;
			//} else if (stars === "") {
			//	alert("별점을 선택해주세요.");
			//	return false;
			} else if (title === "") {
				alert("제목을 입력해주세요.");
				return false;
			} else if (content === "") {
				alert("내용을 입력해주세요.");
				return false;
			} else if (confirm('수정하시겠습니까?')) {
				
				// 내용에서 이미지 태그들 추출하여 파일명을 리스트로 만들기
				let imageFileNameList = [];
				$(content).find('img').each(function() {
					let imageUrl = $(this).attr('src');
					let fileName = imageUrl.split('/').pop();
					imageFileNameList.push(fileName);
				});

				//폼 전송
				$.ajax({
					type: "POST",	//요청 method
					contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
					url: "/reviewModify", //어디 경로로 요청할건지
					data : JSON.stringify({	//객체를 -> JSON string 으로 변환
						reviewId: reviewId,
						userId: userId,
						planId: planId,
						stars: stars,
						title: title,
						content: content,
						imageFileNameList: imageFileNameList
					}),	//파라미터로 같이 담아서 보낼 것들
					success : (data)=>{
						window.location.href = "/reviewView?reviewId="+data;
						//return true;
					},	//요청에 대해 성공한 경우 수행할 내용
					error :	()=>{
						alert('실행 오류');
					}	//요청이 실패,오류난 경우 수행할 내용
					
				});
				
			}
			
		});
		
	</script>
</body>
</html>