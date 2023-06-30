<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- <link href="/css/reviewWrite.css" rel="stylesheet" type="text/css"/> -->
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
<%@ include file="header.jsp" %>

	<h1>reviewWrite</h1>

		여행 <select id="planId" name="planId">
			<option selected value="0">선택해주세요.</option>
			<c:forEach var="join" items="${joinList}">
				<option value="${join.planId}">${join.area}(${join.startDay}~${join.endDay})</option>
			</c:forEach>
		</select><br />
		별점 <select id="stars" name="stars">
			<c:forEach var="i" begin="0" end="10" step="1">
				<option value="${10/2.0 - i/2.0}">${10/2.0 - i/2.0}</option>
			</c:forEach>
		</select><br />
		<input type="text" name="title" id="title" placeholder="제목을 입력해주세요"/><br />
		<textarea id="summernote" name="content"></textarea>
		<br />
		<button type="submit" id="submitBtn">등록하기</button>
		<a href="review"><button type="button">돌아가기</button></a>
		

	
<%@ include file="footer.jsp" %>
<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>
<script src="/js/reviewWrite.js"></script>