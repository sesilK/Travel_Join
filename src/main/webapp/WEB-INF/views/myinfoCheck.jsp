<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/css/login.css" rel="stylesheet" type="text/css"/>
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
<%@ include file="header.jsp" %>
<div class="login-container">

    <div class="login-box">
        <div class="login">
            <form action="/myinfo/before" method="post" id="form-signin">


                <input type="text" class="text input-f" name="userId" value="${sessionScope.userId}">
                <span>login id</span>
                <br>
                <br>

                <input type="password" class="text input-f" name="password">
                <span>password</span>
                <br>

                <button class="signin" type="submit">비밀번호 확인</button>
            </form>
        </div>

    </div>
</div>

<script>
    $(function () {
        $(".modinfo").addClass("is-active");
    });
</script>
<%@ include file="footer.jsp" %>