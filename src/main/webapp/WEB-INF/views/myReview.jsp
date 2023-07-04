<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/css/review.css" rel="stylesheet" type="text/css"/>
<link href="/css/reviewBbs.css" rel="stylesheet" type="text/css"/>
<%@ include file="header.jsp" %>


	<div class="main-header anim">
		<span id="headline">내 후기내역</span>
	</div>
	<div class="center">
		<table class="anim">
			<colgroup>
				<col style="width: 12%;">
				<col style="width: 50%;">
				<col style="width: 13%;">
				<col style="width: 10%;">
				<col style="width: 6%;">
				<col style="width: 6%;">
			  </colgroup>
	        <thead>
	        	<tr>
		            <th class="NotAligned">별점</th>
		            <th class="NotAligned">제목</th>
		            <th>글쓴이</th>
		            <th>작성</th>
		            <th>조회</th>
		            <th>추천</th>
	            </tr>
	        </thead>
	        <tbody id="dataTableBody">
	        </tbody>
		</table>
	</div>
	<div class="center anim">
		<ul id="pagingul">
		</ul>
	</div>
	<div class="center searchBox anim" style="display: none;">
		<select name="searchType" id="searchType" class="select selectType">
				<option value="all" selected>전체</option>
		</select>
		<select name="searchCondition" id="searchCondition" class="select selectCon">
			<option value="user" selected>글쓴이</option>
		</select>
		<input type="text" name="keyword" id="keyword" value="${sessionScope.userId}"/>
	</div>



<%@ include file="footer.jsp" %>
<script src="/js/reviewBbs.js"></script>
<script>
$(function() {
	$(".myReview").addClass("is-active");
});
$(function() {
	$(".review").removeClass("is-active");
});
</script>
