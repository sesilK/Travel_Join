<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/css/login.css" rel="stylesheet" type="text/css"/>
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
<%@ include file="header.jsp" %>
<div class="login-container">

    <div class="login-box">
        <div class="login">
            <h2 class="active sign-in"> sign in </h2>

            <h2 class="nonactive sign-up"> sign up </h2>
            <form action="" method="post">


                <input type="text" class="text input-f" name="userId">
                <span>login id</span>
                <br>
                <br>

                <input type="password" class="text input-f" name="password">
                <span>password</span>
                <br>

                <button class="signin" type="submit">로그인</button>
                <hr>
                <a class="forgot" href="#">Forgot Password?</a>
            </form>
        </div>

        <div class="register" style="display: none">
            <h2 class="nonactive sign-in"> sign in </h2>

            <h2 class="active sign-up"> sign up </h2>
            <form action="/register" method="post">
                <input type="text" class="text input-f" name="userId">
                <span>아이디</span>
                <br>
                <br>

                <input type="password" class="text input-f" name="password">
                <span>비밀번호</span>
                <br>
                <br>

                <input type="text" class="text input-f" name="userId">
                <span>name</span>

                <button class="signin" type="submit">가입</button>
                <hr>
                <a class="forgot" href="#">Forgot Password?</a>
            </form>
        </div>
    </div>
</div>


<%@ include file="footer.jsp" %>
<script src="/js/login.js"></script>
<script>
    $(function () {
        $(".user-login").addClass("is-active");
    });
</script>