<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <link href="/css/chat.css" rel="stylesheet" type="text/css">
</head>
<body data-username="${username}">
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>


<div id="chatbox" class="animate">
    <div id="friendslist">

        <div id="topmenu">
            <span class="chats"></span>
        </div>

        <c:forEach var="room" items="${rooms}">
            <div id="friends">

                <div class="friend" data-roomId="${room.roomId}">
                    <img src="/profile/default_profile.png"/>
                    <p>
                        <strong>채팅방${room.roomId}</strong><br>
                        <span>마지막 채팅</span>
                    </p>
                    <div class="status available"></div>
                </div>

            </div>
        </c:forEach>

    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/js/chatlist.js"></script>
</body>
</html>


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
