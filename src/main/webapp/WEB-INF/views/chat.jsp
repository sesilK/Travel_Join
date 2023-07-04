<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
<link href="/css/chat.css" rel="stylesheet" type="text/css">

<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>


<div id="chatbox" class="animate" data-roomid="${roomId}" data-userid="${sessionScope.userId}">

    <div id="chatview" class="p1">

        <div id="chat-messages">

            <div class="scroll-btn">
                <span class="material-symbols-outlined">stat_minus_2</span>
            </div>

            <%--            <label>2023-06-25</label>--%>

            <c:forEach items="${chats}" var="chat">

                <c:if test="${chat.userId eq sessionScope.userId}">
                    <div class="message right">
                        <img src="/profile/${sessionScope.profileImage}"/>
                        <div class="bubble">
                                ${chat.content}
                            <span class="timestamp">${chat.time}</span>
                            <span class="unread" data-chatid="${chat.chatId}">${chat.unRead}</span>
                        </div>
                    </div>
                </c:if>

                <c:if test="${chat.userId ne sessionScope.userId}">
                    <div class="message">
                        <span class="nick">${chat.userId}</span>
                        <span class="timestamp">${chat.time}</span>
                        <span class="unread" data-chatid="${chat.chatId}">${chat.unRead}</span>
                        <img src="/profile/default_profile.png"/>
                        <div class="bubble">
                                ${chat.content}
                        </div>
                    </div>
                </c:if>

            </c:forEach>

        </div>


        <div id="sendmessage">
            <input type="text" value="메세지를 입력하세요"/>
            <button id="send">전송</button>
        </div>

    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/js/chat.js"></script>
</body>
</html>
