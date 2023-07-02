<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
<link href="/css/myInfo.css" rel="stylesheet" type="text/css"/>
<%@ include file="header.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${not empty sessionScope.userId}">
    <c:set var="user" value="${userDto}"/>
</c:if>


<div class="infoEdit-container">

    <div class="profile-box">
        <div class="image-area">
            <img src="/images/profile/${sessionScope.profileImage}">
            <button type="button" id="btn-image">사진 변경하기</button>
            <input type="file" id="img_file">
            <input type="file" id="empty_file">
        </div>
    </div>

    <div class="infoEdit-box">
        <div class="infoEdit">
            <span>아이디</span><br> <input type="text" class="text input-f" value="${user.userId}" readonly="readonly"><br>
            <div><span>비밀번호</span><br> <input type="password" class="text input-f" name="password"
                                              value="${user.password}"> <i class="fa fa-eye fa-lg"></i></div>
            <br>
            <span>이메일</span><br> <input type="email" class="text input-f" name="email" value="${user.email}"><br>
            <span>닉네임</span><br> <input type="text" class="text input-f" name="nick" value="${user.nick}"><br>
            <span>전화번호</span><br> <input type="tel" class="text input-f" name="tel" value="${user.tel}"><br>


            <div class="genderbox">
                <input type="text" class="text input-f" value="${user.name}" readonly>
                <input type="text" class="text input-f" value="${user.gender == 'M' ? '남자' : '여자'}" readonly>
                <input type="text" class="text input-f" value="${fn:substring(user.birth, 2, 10)}" readonly>
            </div>

            <div class="submitbox">
                <button id="update" type="submit">수정하기</button>
                <button id="dropout" type="submit">탈퇴하기</button>
            </div>

        </div>
    </div>
</div>

</div>
<%@ include file="footer.jsp" %>
<script src="/js/myInfo.js"></script>
