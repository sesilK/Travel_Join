<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<link href="/css/chat.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>


<div id="chatbox" class="animate" data-userid="${sessionScope.userId}">
    <div id="friendslist">

        <c:forEach var="room" items="${rooms}">

            <div class="friend" data-roomId="${room.planId}">
                <img src="/images/profile/${room.fileName}"/>
                <div class="chat-info-box">
                    <div class="box-top">
                        <div class="box-left">
                            <span>${room.title}</span>
                        </div>
                        <div class="box-right">
                            <span>${room.time}</span>
                        </div>
                    </div>
                    <div class="box-bot">
                        <div class="box-left">
                            <span>${room.content}</span>
                        </div>
                        <div class="box-right">
                            <c:if test="${room.chatCount ne 0}">
                                <span>${room.chatCount}</span>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>

    </div>
</div>


<%@include file="footer.jsp" %>
<script>
    $(function () {
        $(".chatting").addClass("is-active");
    });
</script>
<script src="/js/chatlist.js"></script>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--    <script src="https://code.jquery.com/jquery-3.7.0.js"--%>
<%--            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>--%>
<%--    <link href="/css/chat.css" rel="stylesheet" type="text/css">--%>
<%--    <link rel="stylesheet"--%>
<%--          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>--%>
<%--</head>--%>


<%--<c:set var="roomId" value="${room.roomId}"></c:set>--%>
<%--<body>--%>


<%--<div class="chat roomlist">--%>

<%--    <c:forEach var="room" items="${rooms}">--%>
<%--        <div class="chat-list" onclick="location.href='/enter/${room.roomId}'">--%>
<%--            <div class="chat-info">--%>
<%--                <div class="chat-name">채팅방${room.roomId}</div>--%>
<%--                <div class="chat-last">${room.content}</div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>

<%--</div>--%>

<%--<embed type="text/html" src="/roomlist" width="800" height="500" hidden>--%>

<%--</body>--%>
<%--</html>--%>
