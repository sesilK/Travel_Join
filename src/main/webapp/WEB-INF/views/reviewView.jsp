<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>reviewView</h1>
	
	<a href="reviewBbs"><button>글목록</button></a>
	
	<p id="num" data-reviewId="${item.reviewId}">제목 ${item.title}</p>
	<p>여행 ${item.planId}</p>
	<p>별점 ${item.stars}</p>
	<p>${item.userId} | ${item.createDate} | 조회 ${item.views} | 추천 <span id="likeCount">${item.likeCount}</span> | 댓글 ${item.commentCount}</p>
	<c:if test="${item.updateDate != null}">
		<p>${item.updateDate} 수정됨</p>
	</c:if>

	<c:if test="${item.userId == 'admin'}">
	<%-- <c:if test="${item.userId eq sessionScope.userId}"> --%>
	  <button id="modifyBtn" type="button">수정</button>
	  <button id="deleteBtn" type="button">삭제</button><br/>
	</c:if>

	<p>내용 : ${item.content}</p>

	<button id="like" type="submit">추천</button>


		
	<hr/>
	<form action="" method="POST">
		<p>댓글(${item.commentCount})</p>
		<textarea name="comment"></textarea>
		<button type="submit">등록</button>
	</form>
	
	<table>
        <tbody>
        	<c:forEach var="comment" items="${commentList}">
	            <tr>
	            	<td>${comment.userId}</td>
	            	<td>${comment.content}</td>
	            	<td>${comment.createDate}</td>
	            </tr>
			</c:forEach>
        </tbody>
	</table>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
	
		$(document).ready(function(){
			
			let reviewId = $('#num').data('reviewid');
			let userId = '${item.userId}';
			
			$('#like').click(function(){

				$.ajax({
					type : "POST",	//요청 method
					contentType : "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
					url : "/reviewLike",	//어디 경로로 요청할건지 (Restful Api 서버 요청 주소)
					data : JSON.stringify({	//객체를 -> JSON string 으로 변환
						userId: userId,
						reviewId: reviewId
					}),	//파라미터로 같이 담아서 보낼 것들
					success : (data)=>{
						console.log(data);
						if(data === 'true'){
							let likeCount = $('#likeCount'); //추천수 요소
							let currentLikeCount = parseInt(likeCount.text()); //현재 추천수
							likeCount.text(currentLikeCount+1); // 추천 수 업데이트
							alert('추천하였습니다.');
						}
						if(data === 'false'){
							alert('이미 추천한 글입니다.');
						}
					},	//요청에 대해 성공한 경우 수행할 내용
					error :	()=>{
						alert('실행 오류');
					}	//요청이 실패,오류난 경우 수행할 내용
				});
			
			});
			
			// 수정 버튼 클릭 이벤트 처리
			$('#modifyBtn').click(function(){
				window.location.href = "/reviewModify?reviewId="+reviewId;
			});
			
			// 삭제 버튼 클릭 이벤트 처리
			$('#deleteBtn').click(function(){

				if(confirm('삭제하시겠습니까?')){
				    $.ajax({
				        type: "POST",
				        contentType : "application/json; charset=utf-8",
				        url: "/deleteReview",
				        data: JSON.stringify({
				        	userId: userId,
				        	reviewId: reviewId
				    	}),
					    success: function(data){
					        if(data === 'true'){
					            alert('게시물이 삭제되었습니다.');
					            window.location.href = "/reviewBbs";
					        } else {
					            alert('삭제 권한이 없습니다.');
					        }
					    },
					    error: function(){
					        alert('게시물 삭제 실행 오류');
					    }
					});
				}
			});
			
			
		});

	</script>
</body>
</html>