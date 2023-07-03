<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/css/review.css" rel="stylesheet" type="text/css"/>
<link href="/css/reviewBbs.css" rel="stylesheet" type="text/css"/>
<%@ include file="header.jsp" %>


	<div class="main-header anim">
		<span id="headline">여행리뷰</span><button id="write">글쓰기</button>
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
	        	<%-- <c:forEach var="item" items="${reviewList}">
		            <tr>
		            	<td class="stars">
		            		<span class="rating_box">
								<span class="rating">★★★★★
									<span class="rating_star" data-stars="${item.stars}">★★★★★</span>
								</span>
							</span>
		            	</td>
		            	<td class="title" data-reviewid="${item.reviewId}">${item.area} │ ${item.title}
		            	    <c:if test="${item.commentCount > 0}">[${item.commentCount}]</c:if></td>
		            	<td>${item.nick}</td>
		            	<td>${item.createDate}</td>
		            	<td>${item.views}</td>
		            	<td>${item.likeCount}</td>
		            </tr>
				</c:forEach> --%>
	        </tbody>
		</table>
	</div>
	<div class="center anim">
		<ul id="pagingul">
		</ul>
	</div>
	<div class="center searchBox anim">
		<select name="searchType" id="searchType" class="select selectType">
				<option value="all" ${param.searchType eq 'all' ? 'selected' : ''}>전체</option>
				<option value="domestic" ${param.searchType eq 'domestic' ? 'selected' : ''}>국내</option>
				<option value="overseas" ${param.searchType eq 'overseas' ? 'selected' : ''}>해외</option>
		</select>
		│<select name="searchCondition" id="searchCondition" class="select selectCon">
				<option value="all">키워드</option>
				<%-- <option value="title" ${param.searchCondition eq 'title' ? 'selected' : ''}>제목</option>
				<option value="content" ${param.searchCondition eq 'content' ? 'selected' : ''}>내용</option> --%>
				<option value="ticon" ${param.searchCondition eq 'ticon' ? 'selected' : ''}>제목+내용</option>
				<option value="user" ${param.searchCondition eq 'user' ? 'selected' : ''}>글쓴이</option>
				<option value="area" ${param.searchCondition eq 'area' ? 'selected' : ''}>여행지</option>
		</select>
		<input type="text" name="keyword" id="keyword" value="${keyword}"/>
		<button id="searchBtn" class="emojiBtn">🔍</button>
	</div>



<%@ include file="footer.jsp" %>
<script src="/js/reviewBbs.js"></script>