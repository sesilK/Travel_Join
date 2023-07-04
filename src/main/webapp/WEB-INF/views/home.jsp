<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<div class="main-header anim" style="--delay: 0s">인기 급상승</div>
<div class="main-blogs">
    <div class="main-blog anim" style="--delay: .1s">
        <div class="main-blog__title">여기를<br>뭐로쓰지</div>
        <div class="main-blog__author">
            <div class="author-img__wrapper">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="round"
                    stroke-linejoin="round" class="feather feather-check">
                    <path d="M20 6L9 17l-5-5"/>
                </svg>
                <img class="author-img"
                    src="https://images.unsplash.com/photo-1560941001-d4b52ad00ecc?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80"/>
            </div>
            <div class="author-detail">
                <div class="author-name">토종한국인</div>
                <div class="author-info">5312 views<span class="seperate"></span>23-06-27</div>
            </div>
        </div>
        <div class="main-blog__time">1211 ❤</div>
    </div>
    <div class="main-blog anim" style="--delay: .2s">
        <div class="main-blog__title">슬라이드 배너?</div>
        <div class="main-blog__author tips">
            <div class="main-blog__time">762 ❤</div>
            <div class="author-img__wrapper">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="round"
                    stroke-linejoin="round" class="feather feather-check">
                    <path d="M20 6L9 17l-5-5"/>
                </svg>
                <img class="author-img"
                    src="https://images.unsplash.com/photo-1496345875659-11f7dd282d1d?ixid=MXwxMjA3fDB8MHxzZWFyY2h8Mzl8fG1lbnxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"/>
            </div>
            <div class="author-detail">
                <div class="author-name">짜장면곱빼기</div>
                <div class="author-info">3242 views<span class="seperate"></span>23-06-21</div>
            </div>
        </div>
    </div>
</div>
<div class="small-header anim" style="--delay: .3s">최신글</div>
<div class="plan-list">

    <img alt="" src="/images/여기어때.jpg">
    <img alt="" src="/images/모두투어.jpg">
    <img alt="" src="/images/스카이스캐너.jpg">
    <img alt="" src="/images/에어비엔비.png">
    <img alt="" src="/images/호텔스 컴바인.png">
    
</div>

<%@ include file="footer.jsp" %>
<script>
    $(function () {
        $(".discover").addClass("is-active");
    });
</script>