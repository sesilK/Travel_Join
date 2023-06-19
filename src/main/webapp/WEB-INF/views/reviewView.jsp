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
	
	<p>제목 ${item.title}</p>
	<p>여행 ${item.planId}</p>
	<p>별점 ${item.stars}</p>
	<p>${item.userId} | ${item.createDate} | 조회 ${item.views} | 추천 ${item.likeCount} | 댓글 ${item.commentCount}</p>
	<p>내용 : ${item.content}</p>

	<form action="" method="POST" onsubmit="showAlert()">
		<button type="submit">추천</button>
	</form>
		
	<hr/>
<%-- 	<form action="" method="POST">
		<p>댓글(${item.commentCount})</p>
		<textarea name="comment"></textarea>
		<button type="submit">등록</button>
	</form> --%>
	
	<table>
        <tbody>
        	<c:forEach var="item" items="${commentList}">
	            <tr>
	            	<td>${item.userId}</td>
	            	<td>${item.content}</td>
	            	<td>${item.createDate}</td>
	            </tr>
			</c:forEach>
        </tbody>
	</table>
	
	<script>
        function showAlert() {
        	alert("${alertMessage}");
        }
	</script>
</body>
</html>