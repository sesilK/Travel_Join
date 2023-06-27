<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="${path}/css/register.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
    <form action="" method="post">
        <div class="f-top">
            아이디<input type="text" name="userId" placeholder="아이디">
            비밀번호<input type="password" name="password" placeholder="비밀번호">
            이메일<input type="email" name="email" placeholder="이메일">
            이름<input type="text" name="name" placeholder="이름">
            닉네임<input type="text" name="nick" placeholder="닉네임">
            번호<input type="tel" name="tel" placeholder="전화번호">
            주민번호<input type="text" name="birth" placeholder="7자리까지 입력">
        </div>

        <button type="submit">회원가입</button>
    </form>
</div>

</body>
</html>
