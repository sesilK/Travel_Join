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