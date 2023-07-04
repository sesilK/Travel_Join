<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<link href="/css/join_view.css" rel="stylesheet" type="text/css"/>
<div class="plan-list">

    <c:forEach var="item" items="${items}">
        <div class="card" data-planid="${item.planId}">
            <div class="card-header">
                <div class="left">
                    <div class="profile-image">
                        <img src="/images/profile/${item.fileName}"
                            alt="avatar">
                    </div>
                    <div class="profile-info">
                        <span>${item.nick}</span>
                        <span>${item.birth}살 • ${item.gender}</span>
                    </div>
                </div>
                <div class="right">
                    <div class="plan">
                        <span class="material-symbols-rounded">calendar_month</span>여행기간
                    </div>
                    <div class="date">
                            ${item.personnel}명 • &nbsp;<span>${item.startDay} ~ ${item.endDay}</span>
                    </div>
                </div>
            </div>

            <div class="card-body">
                <c:choose>
                    <c:when test="${not empty item.firstImage}">
                        ${item.firstImage}
                    </c:when>
                    <c:otherwise>
                        <img src="/images/join/default.png" style="width: 677.778px;">
                    </c:otherwise>
                </c:choose>

                <div class="content">
                        ${item.content}
                </div>
            </div>

            <div class="card-footer">
                <div class="title">${item.title}</div>
                
            </div>
        </div>
    </c:forEach>

    <div class="btbox">
        <a href="/join_making"><button class="makingUpdate" type="button">작성하기</button></a>
    </div>
</div>
<script>
    $(function () {
        $(function () {
            $(".trending").addClass("is-active");
        });

        $(".card").click(function () {
            location.href = "/detail/" + $(this).data("planid");
        });
    })
</script>
<%@ include file="footer.jsp" %>
