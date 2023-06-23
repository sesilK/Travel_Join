<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>채팅방 리스트</h1>

<ul>
    <c:forEach var="room" items="${rooms}">
        <li>
            <p>
                <a href="/enter/${room.roomId}">채팅방이름</a>
            </p>
        </li>
    </c:forEach>

</ul>

</body>
</html>
