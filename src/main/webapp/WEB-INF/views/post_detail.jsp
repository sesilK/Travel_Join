<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="/css/join_detail.css"/>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<body data-planid="${item.planId}">


<div class="d-card-wrapper">
    <div class="d-card">
        <!-- card left -->
        <div class="post-imgs">

            <div class="img-display">
                <div class="img-showcase">
                    <c:if test="${images.size() > 0}">
                        <c:forEach var="i" begin="0" end="${images.size()-1}">
                            <img src="${images.get(i).fileName}">
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <div class="img-select">
                <c:if test="${images.size() > 0}">
                    <c:forEach var="i" begin="0" end="${images.size()-1}">
                        <div class="img-item">
                            <a href="#" data-id="${i+1}"> <img src="${images.get(i).fileName}">
                            </a>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

        </div>
        <!-- card right -->
        <div class="post-content">
            <h2 class="post-title">제목 ${item.title}</h2>

            <div class="post-detail">
                <h2>본문 :</h2>
                <p>내용 ${item.content}</p>

                <ul>
                    <c:choose>
                        <c:when test="${item.planType eq 0}">
                            <li>구 분 : <span>국내</span></li>
                        </c:when>
                        <c:when test="${item.planType eq 1}">
                            <li>구 분 : <span>해외</span></li>
                        </c:when>
                    </c:choose>
                    <li>구 분 : <span>${item.planType}</span></li>
                    <li>여행지 : <span>${item.destination}</span></li>
                    <li>여행시작 : <span>이때부터 ${item.startDay}</span></li>
                    <li>여행종료 : <span>이때까지 ${item.endDay}</span></li>
                    <li>모집 마감일: <span>${item.finishDate}</span></li>
                    <li>모집 상태: <span>${item.planState}</span></li>
                    <li>조회수: <span>${item.views}</span></li>
                    <li>추천: <span id="likes">${item.likes}</span></li>
                    <button type="button" id="btn-like">추천하기</button>
                </ul>
            </div>

            <div class="accompany-button">
                <button type="button" id="btn-join" class="btn">동행 신청하기</button>
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
            <%--            <button type="button" id="btn-join">참가하기</button>--%>
        </c:when>
    </c:choose>
</div>
</c:if>

<%@include file="footer.jsp" %>
<script src="/js/post_detail.js"></script>
