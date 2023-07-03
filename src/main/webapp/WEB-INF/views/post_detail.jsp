<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/post_detail.css">
<!-- 인터셉터 적용된 외부css 적용 -->

<script type="text/javascript" src="javascript/post_detail.js"></script>
<!-- 인터셉터 적용된 외부js 적용 -->
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<!-- 페북/트위터/인스타 i태그 아이콘이미지적용 -->
<title>여행마늘</title>
</head>

<body>
	<div class="card-wrapper">
		<div class="card">
			<!-- card left -->
			<div class="post-imgs">
			
				<div class="img-display">
				
					<div class="img-showcase">
						<img src="https://cdn.pixabay.com/photo/2019/07/25/17/09/camp-4363073_640.png" alt="게시글 첨부이미지1"> 
						<img src="https://cdn.pixabay.com/photo/2018/06/27/17/48/fantasy-3502188_640.jpg" alt="게시글 첨부이미지2"> 				
					</div>
				</div>
				<div class="img-select">
					<div class="img-item">
						<a href="#" data-id="1"> <img src="https://cdn.pixabay.com/photo/2019/07/25/17/09/camp-4363073_640.png" alt="게시글 첨부이미지1">
						</a>
					</div>
					
					<div class="img-item">
						<a href="#" data-id="2"> <img src="https://cdn.pixabay.com/photo/2018/06/27/17/48/fantasy-3502188_640.jpg" alt="게시글 첨부이미지2">
						</a>
					</div>
					

	
				</div>
			</div>
			<!-- card right -->
			<div class="post-content">
				<h2 class="post-title">제목 ${item.title}</h2>

				<div class="post-detail">
					<h2>about this plan :</h2>
					<img src="">
					<p>내용 ${item.content}</p>

					<ul>
						<li>구 분 : <span>planType</span></li>
						<li>여행지 : <span>destination </span></li>
						<li>여행시작 : <span>이때부터 ${item.startDay.substring(0,10)}</span></li>
						<li>여행종료 : <span>이때까지 ${item.endDay.substring(0,10)}</span></li>
						<li>모집 마감일: <span>finishDate</span></li>
						<li>모집 상태: <span>planState / 마감/ 모집중</span></li>
					</ul>
				</div>

				<div class="accompany-button">
					<button type="button" class="btn">동행 신청하기</button>
				</div>

				<div class="social-links">
					<p>Share At :</p>
					<a href="#"> <i class="fab fa-facebook-f"></i>
					</a> <a href="#"> <i class="fab fa-twitter"></i>
					</a> <a href="#"> <i class="fab fa-instagram"></i>
					</a>
				</div>
			</div>
		</div>
	</div>

	<!-- 동행신청한 멤버 목록 -->
	<h2>모집 인원</h2>
	<ul>
		<c:forEach var="member" items="${partyMembers}">
			<li>${member.userId}</li>
		</c:forEach>
	</ul>

</body>

</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="/css/join_detail.css"/>

<body data-planid="${item.planId}">

<p>모집 제목 : ${item.title}</p>
<p>모집 마감날짜: ${item.finishDate}</p>
<p>지역: ${item.destination}</p>
<p>모집인원: ${CurrPersonnel}/${item.personnel}명</p>
<p>여행기간: ${item.startDay} ~ ${item.endDay}</p>
<p>방장아이디: ${item.userId}</p>
<p>조회수: ${item.views}</p>
<p>추천: ${item.likes}</p>

<c:if test="${not empty sessionScope.userId}">
<div class="button-box">
    <c:choose>
        <c:when test="${item.planState eq 1}">
            <p>마감된 모집입니다.</p>
        </c:when>

        <c:when test="${sessionScope.userId eq item.userId}">
            <button type="button" id="btn-end">모집마감</button>
            <button type="button" id="btn-mod">내용수정</button>
            <button type="button" id="btn-del">글 삭제</button>
        </c:when>

        <c:when test="${item.planState eq 0}">
            <button type="button" id="btn-join">참가하기</button>
        </c:when>
    </c:choose>
</div>
</c:if>

<%@include file="footer.jsp" %>
<script src="/js/post_detail.js"></script>
>>>>>>> e326f5cd561d7f37949b95509f368b795511a199
