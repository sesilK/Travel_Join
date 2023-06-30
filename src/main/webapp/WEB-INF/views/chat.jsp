<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
    <link href="/css/chat.css" rel="stylesheet" type="text/css">
</head>
<body data-username="${sessionScope.userId}">
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>


<div id="chatbox" class="animate" data-roomid="${roomId}">
    <%--    <div id="friendslist">--%>

    <%--        <div id="topmenu">--%>
    <%--            <span class="chats"></span>--%>
    <%--        </div>--%>

    <%--        <c:forEach var="room" items="${rooms}">--%>
    <%--            <div id="friends">--%>

    <%--                <div class="friend" data-roomId="${room.roomId}">--%>
    <%--                    <img src="/profile/default_profile.png"/>--%>
    <%--                    <p>--%>
    <%--                        <strong>채팅방${room.roomId}</strong><br>--%>
    <%--                        <span>마지막 채팅</span>--%>
    <%--                    </p>--%>
    <%--                    <div class="status available"></div>--%>
    <%--                </div>--%>

    <%--            </div>--%>
    <%--        </c:forEach>--%>

    <%--    </div>--%>

    <div id="chatview" class="p1">
        <div id="profile">

            <div id="close">
                <div class="cy"></div>
                <div class="cx"></div>
            </div>

            <p>채팅방 제목</p>

        </div>

        <div id="chat-messages">
            <div class="scroll-btn">
                <span class="material-symbols-outlined">stat_minus_2</span>
            </div>

            <%--            <label>2023-06-25</label>--%>

            <c:forEach items="${chats}" var="chat">
                <c:if test="${chat.type eq 'text'}">



                    <c:if test="${chat.sender eq sessionScope.userId}">
                        <div class="message right">
                            <img src="/profile/default_profile.png"/>
                            <div class="bubble">
                                    ${chat.content}
                                <span class="timestamp">${chat.timeStamp}</span>
                                <span class="unread" data-chatid="${chat.chatId}">${chat.unRead}</span>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${chat.sender!= sessionScope.userId}">
                        <div class="message">
                            <span class="nick">${chat.sender}</span>
                            <span class="timestamp">${chat.timeStamp}</span>
                            <span class="unread" data-chatid="${chat.chatId}">${chat.unRead}</span>
                            <img src="/profile/default_profile.png"/>
                            <div class="bubble">
                                    ${chat.content}
                            </div>
                        </div>
                    </c:if>

                </c:if>
            </c:forEach>

        </div>


        <div id="sendmessage">
            <input type="text" value="메세지를 입력하세요"/>
            <button id="send"></button>
        </div>

    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/js/chat.js"></script>
</body>
</html>
