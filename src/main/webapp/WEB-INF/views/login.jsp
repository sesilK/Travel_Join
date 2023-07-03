<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/css/login.css" rel="stylesheet" type="text/css"/>
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
<%@ include file="header.jsp" %>
<div class="login-container">

    <div class="login-box">
        <div class="login">
            <h2 class="active sign-in"> sign in </h2>

            <h2 class="nonactive sign-up"> sign up </h2>
            <form action="/login" method="post" id="form-signin">


                <input type="text" class="text input-f" name="userId">
                <span>login id</span>
                <br>
                <br>

                <input type="password" class="text input-f" name="password">
                <span>password</span>
                <br>

                <button class="signin" type="button">로그인</button>
                <hr>
                <a class="forgot" href="#">Forgot Password?</a>
            </form>
        </div>
        
        <!-- 회원가입-->
        
        <div class="register" style="display: none">
            <h2 class="nonactive sign-in"> sign in </h2>

            <h2 class="active sign-up"> sign up </h2>

            <form action="/register" method="post" id="form-signup">
                <input type="text" id="input_id" class="text input-f" name="userId">
                <span>아이디</span><br><br>

                <input type="password" id="input_pw" class="text input-f" name="password">
                <span>비밀번호</span><br><br>

                <input type="text" id="input_email" class="text input-f" name="email">
                <span>이메일</span><br><br>

                <input type="text" id="input_name" class="text input-f" name="name">
                <span>이름</span><br><br>

                <input type="text" id="input_nick" class="text input-f" name="nick">
                <span>닉네임</span><br><br>

                <input type="text" id="input_tel" id="input_tel" class="text input-f" name="tel">
                <span>전화번호</span>

                <input type="text" id="birth" name="birth" hidden>

                <div class="sel-box">
                    <select class="sel-text" name="gender">
                        <option selected>성별</option>
                        <option value="F">여자</option>
                        <option value="M">남자</option>
                    </select>

                    <select class="sel-text" id="year">
                        <option selected>년도</option>
                        <c:set var="year" value="2023"></c:set>
                        <c:forEach var="i" begin="1900" end="${year}" step="1">
                            <option value="${year - i + 1900}">${year - i + 1900}년</option>
                        </c:forEach>
                    </select>

                    <select class="sel-text" id="month">
                        <option selected>월</option>
                        <c:forEach var="i" begin="1" end="12" step="1">
                            <option value="${i}">${i}월</option>
                        </c:forEach>
                    </select>

                    <select class="sel-text" id="day">
                        <option selected>일</option>
                        <c:forEach var="i" begin="1" end="31" step="1">
                            <option value="${i}">${i}일</option>
                        </c:forEach>
                    </select>
                </div>




                <button class="signin" type="submit">가입</button>
                
            </form>
        </div>
    </div>
</div>


<%@ include file="footer.jsp" %>
<script src="/js/login.js"></script>
<script>
    $(function () {
        <c:if test="${not empty success}">
        alert("${success}");
        </c:if>

        $(".user-login").addClass("is-active");
    });


</script>