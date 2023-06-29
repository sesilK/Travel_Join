<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<title>Insert title here</title>
</head>
<body>
		제 목 <input type="text" name="title"><br/>
		여 행 지 <input type="radio" checked="checked" value="국내" name="destination"> 국내 <input type="radio" value="해외" name="destination"> 해외<br/>
		여행 기간 <input type="date" name="startDay"> ~ <input type="date" name="endDay"><br/>
		인 원 수 <input type="number" min="1" max="100" name="personnel">
		마 감 일 <input type="date" name="finishDate">
		<textarea id="summernote" name="content"></textarea>
		<button type="button" id="submitBtn">글올리기</button>
	
	<script type="text/javascript">
	$(document).ready(function() {
		  $('#summernote').summernote({
			width : 800, height : 400,
			minHeight : null, maxHeight : null,			  
			toolbar: [
				     ['fontsize', ['fontsize']],
				     ['style', ['bold']],
				     ['insert',['picture']]
				  	 ],
			fontSizes: ['10','20','30','40','50','60','70','80','90','100'],
			callbacks: {
			    onImageUpload: function(files, editor, welEditable) { //이미지 첨부
			        for (let i = files.length - 1; i >= 0; i--) { // 다중 업로드
			            uploadSummernoteImageFile(files[i], this);
			        }
			    }
			}
		  
		  });  
	});
			
			//글쓰기 중 사진을 넣을때 저장시키고 불러오는 함수
			function uploadSummernoteImageFile(file, el) {
				let formData = new FormData();
				formData.append("file", file);
				$.ajax({
					url : "/uploadSummernoteImageFile",	//Controller 요청 주소
					type : "POST",
					enctype : 'multipart/form-data',
					data : formData,
					cache: false,
					contentType : false,
					processData : false,
					success : (data)=> {
						$(el).summernote('editor.insertImage', data.url);
					}
				});			 
			}
			document.getElementById("submitBtn").addEventListener("click", function() {
				// 글작성올리기전 검증(빈칸)후에 비동기로 db에 전송
				let titlt = $('input[name="title"]').val();
				let destination = $('input[name="destination"]').val();
				let startDay = $('input[name="startDay"]').val();
				let endDay = $('input[name="endDay"]').val();
				let personnel = $('input[name="personnel"]').val();
				let finishDate = $('input[name="finishDate"]').val();
				let content = $('textarea[name="content"]').innerHTML;
					
					if(titlt===""){
						alert("제목을 입력해주세요")
						return false;
					}else if (destination ===""){
						alert("여행지를 선택해주세요")
						return false;
					}else if (startDay===""){
						alert("시작일을 입력해주세요")
						return false;
					}else if (endDay===""){
						alert("종료일을 입력해주세요")
						return false;
					}else if (personnel===""){
						alert("인원수를 입력해주세요")
						return false;
					}else if (finishDate===""){
						alert("마감일을 입력해주세요")
						return false;
					}else if (content===""){
						alert("본문을 입력해주세요")
						return false;
					}else{
					
						let content = $('#summernote').summernote('code'); //에디터 내용가져오기
						
						let imageFileNameList = []; //내용 중 이미지 태그를 추출 후 리스트로 만들기
						$(content).find('img').each(function() {
							let imageUrl = $(this).attr('src');
							let fileName = imageUrl.split('/').pop();
							imageFileNameList.push(fileName);
						});

						$.ajax({
							type: "POST",	//요청 method
							contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
							url: "/joinmaking_process", //어디 경로로 요청할건지
							data : JSON.stringify({	//JSON string 으로 변환
								title: titlt,
								destination: destination,
								startDay: startDay,
								endDay: endDay,
								personnel: personnel,
								content: content,
								finishDate: finishDate,
								imageFileNameList:imageFileNameList
							}),	//파라미터로 같이 담아서 보낼 것들
							success : (data)=>{
								window.location.href = "/join_view";
								return true;
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