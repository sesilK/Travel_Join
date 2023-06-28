<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/css/reviewBbs.css" rel="stylesheet" type="text/css"/>
<%@ include file="header.jsp" %>


	<h1>reviewBbs</h1>
	
	<a href="reviewWrite"><button>글쓰기</button></a>
	
	<table>
        <thead>
        	<tr>
	            <th>별점</th>
	            <th>여행지</th>
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
	            	<td>${item.planInfo}</td>
	            	<td class="title" data-reviewid="${item.reviewId}">${item.title}
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
				<option value="destination" ${param.searchCondition eq 'destination' ? 'selected' : ''}>여행지</option>
		</select>
		<input type="text" name="keyword" value="${param.keyword}"/>
		<button type="submit">검색</button>
	</form>
	


<%@ include file="footer.jsp" %>
<script src="/js/reviewBbs.js"></script>