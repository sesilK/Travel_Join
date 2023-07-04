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
                    <c:choose>
                        <c:when test="${images.size() > 0}">
                            <c:forEach var="i" begin="0" end="${images.size()-1}">
                                <img src="/images/join/${images.get(i).fileName}">
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <img src="/images/join/default.png">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="img-select">
                <c:choose>
                    <c:when test="${images.size() > 0}">
                        <c:forEach var="i" begin="0" end="${images.size()-1}">
                            <div class="img-item">
                                <a href="#" data-id="${i+1}"> <img src="/images/join/${images.get(i).fileName}">
                                </a>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="img-item">
                            <a href="#" data-id="1"> <img src="/images/join/default.png">
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
        <!-- card right -->
        <div class="post-content">
            <p class="post-title">${item.title}</p>

            <div class="post-detail">
                <p>${item.content}</p>

                <ul>
                    <c:choose>
                        <c:when test="${item.planType eq 0}">
                            <li>구 분 : <span>국내</span></li>
                        </c:when>
                        <c:when test="${item.planType eq 1}">
                            <li>구 분 : <span>해외</span></li>
                        </c:when>
                    </c:choose>
                    <li>여행지 : <span>${item.destination}</span></li>
                    <li>여행시작 : <span>${item.startDay}일 부터</span></li>
                    <li>여행종료 : <span>${item.endDay}일 까지</span></li>
                    <li>모집 마감일: <span>${item.finishDate}일</span></li>
                    <li>모집 상태: <span>${item.planState}</span></li>
                    <li>조회수: <span>${item.views}</span></li>
                    <li>추천: <span id="likes">${item.likes}</span></li>
                </ul>
            </div>

            <div class="accompany-button">
                <button type="button" id="btn-like" class="btn">추천하기</button>
                <button type="button" id="btn-join" class="btn">동행 신청하기</button>
                <c:if test="${sessionScope.userId eq item.userId}">
                    <br>
                    <button type="button" id="btn-end" class="btn deleteBtn">모집 마감</button>
                    <button type="button" id="btn-del" class="btn deleteBtn">모집 삭제</button>
                </c:if>
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

<%@include file="footer.jsp" %>
<script src="/js/post_detail.js"></script>
