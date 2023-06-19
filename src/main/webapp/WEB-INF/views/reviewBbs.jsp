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

	<h1>reviewBbs</h1>
	
	<a href="reviewWrite"><button>글쓰기</button></a>
	
	<form action="" method="GET">
		<select name="searchCondition">
				<option>검색조건</option>
				<option value="title" ${param.searchCondition eq 'title' ? 'selected' : ''}>제목</option>
				<option value="content" ${param.searchCondition eq 'content' ? 'selected' : ''}>내용</option>
				<option value="ticon" ${param.searchCondition eq 'ticon' ? 'selected' : ''}>제목+내용</option>
				<option value="user" ${param.searchCondition eq 'user' ? 'selected' : ''}>글쓴이</option>
		</select><br/>
		<input type="text" name="keyword" value="${param.keyword}"/><br/>
		<button type="submit">검색</button>
	</form>
	
	<table>
        <thead>
        	<tr>
	            <th>작성일</th>
	            <th>제목</th>
	            <th>글쓴이</th>
	            <th>조회</th>
	            <th>추천</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="item" items="${reviewList}">
	            <tr>
	            	<td>${item.createDate}</td>
	            	<td><a href="reviewView?reviewId=${item.reviewId}">${item.title}</a>
	            	    <c:if test="${item.commentCount > 0}">[${item.commentCount}]</c:if></td>
	            	<td>${item.userId}</td>
	            	<td>${item.views}</td>
	            	<td>${item.likeCount}</td>
	            </tr>
			</c:forEach>
        </tbody>
	</table>
	
	
</body>
</html>