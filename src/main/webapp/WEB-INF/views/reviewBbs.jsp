<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/reviewBbs.css">
</head>
<body>

	<h1>reviewBbs</h1>
	
	<a href="reviewWrite"><button>글쓰기</button></a>
	
	<table>
        <thead>
        	<tr>
	            <th>별점</th>
	            <th>제목</th>
	            <th>글쓴이</th>
	            <th>작성</th>
	            <th>조회</th>
	            <th>추천</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="item" items="${reviewList}">
	            <tr>
	            	<td>${item.stars}</td>
	            	<td class="title" data-reviewId="${item.reviewId}">${item.title}
	            	    <c:if test="${item.commentCount > 0}">[${item.commentCount}]</c:if></td>
	            	<td>${item.nick}</td>
	            	<td>${item.createDate}</td>
	            	<td>${item.views}</td>
	            	<td>${item.likeCount}</td>
	            </tr>
			</c:forEach>
        </tbody>
	</table>
	
	
	<form action="" method="GET">
		<select name="searchCondition">
				<option>검색조건</option>
				<option value="title" ${param.searchCondition eq 'title' ? 'selected' : ''}>제목</option>
				<option value="content" ${param.searchCondition eq 'content' ? 'selected' : ''}>내용</option>
				<option value="ticon" ${param.searchCondition eq 'ticon' ? 'selected' : ''}>제목+내용</option>
				<option value="user" ${param.searchCondition eq 'user' ? 'selected' : ''}>글쓴이</option>
		</select>
		<input type="text" name="keyword" value="${param.keyword}"/>
		<button type="submit">검색</button>
	</form>
	
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
	$(document).ready(function(){

		// ${item.title} 클릭 이벤트 처리
		$('body').on('click', 'td.title', function(){
			let reviewId = $(this).data('reviewid');
			$.ajax({
				type : "POST",	//요청 method
				contentType : "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
				url : "/reviewViewIsExist",	//어디 경로로 요청할건지 (Restful Api 서버 요청 주소)
				data : JSON.stringify({	//객체를 -> JSON string 으로 변환
					reviewId: reviewId
				}),	//파라미터로 같이 담아서 보낼 것들
				success : (data)=>{
					if(data === 'true'){
						window.location.href = "/reviewView?reviewId="+reviewId;
					}
					if(data === 'false'){
						alert("삭제된 글입니다.");
					}
				},	//요청에 대해 성공한 경우 수행할 내용
				error :	()=>{
					alert('실행 오류');
				}	//요청이 실패,오류난 경우 수행할 내용
			});
		});
		
	});
	
	</script>
	
	
</body>
</html>