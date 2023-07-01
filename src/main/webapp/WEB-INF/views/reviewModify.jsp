<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/css/review.css" rel="stylesheet" type="text/css"/>
<!-- <link href="/css/reviewWrite.css" rel="stylesheet" type="text/css"/> -->
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
<%@ include file="header.jsp" %>

	<div class="main-header anim">
		<span id="headline">수정하기</span><button id="modifyBtn">발행</button>
	</div>
	<div class="center">
		<select id="planId" name="planId" data-reviewid="${reviewId}">
			<option selected value="0">여행 선택</option>
			<c:forEach var="join" items="${joinList}">
				<option value="${join.planId}">${join.area}(${join.startDay}~${join.endDay})</option>
			</c:forEach>
		</select>
		<select id="stars" name="stars">
			<c:forEach var="i" begin="0" end="10" step="1">
				<option value="${10/2.0 - i/2.0}">${10/2.0 - i/2.0}</option>
			</c:forEach>
		</select>
	</div>
	<div class="center">
		<input type="text" name="title" id="title" placeholder="제목을 입력해주세요"/>
	</div>
	<div class="center">
		<textarea id="summernote" name="content"></textarea>
	</div>
	
<%@ include file="footer.jsp" %>
<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>
<script src="/js/reviewModify.js"></script>