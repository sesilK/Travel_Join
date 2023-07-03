<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="/css/join_detail.css"/>

<body data-planid="${item.planId}">
	<div class="detail">
	<div class="detail_title"><h1>${item.title}</h1></div>
	<div class="detail_Information"> 
		<div class="TripCaptain">
		<div class="TripCaptain_img">
		<img src="/images/profile/${item.fileName}"/>
		</div>
		<div class="TripCaptain_info">
		<p>${item.nick}</p>
        <p>${item.birth}살</p>
		<p>${item.gender}</p>
		</div>
		</div>
	<div class="Travelplan">
	<p>지역: ${item.destination}</p> 
	<p>모집인원: ${CurrPersonnel}/${item.personnel}명</p> 	
	<p>모집 마감날짜: ${item.finishDate}</p>
	<p>여행기간: ${item.startDay} ~ ${item.endDay}</p>
	</div>
	</div>
	<div class="detail_center">
		
		${item.content}
	<p>조회수: ${item.views} 추천: ${item.likes}</p>
		
	</div>
	
	<c:if test="${not empty sessionScope.userId}">
<div class="detail_footer">	
	<div class="Info">
		
	</div>
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
</div>
</c:if>
</div>

<%@include file="footer.jsp" %>
<script src="/js/post_detail.js"></script>