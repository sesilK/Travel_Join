<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1>login</h1>
	<div class="loginbox">
		<form action="" method="post">
			아이디 <input type="text" name="userId" placeholder="아이디">
			비밀번호 <input type="password" name="password" placeholder="비밀번호">
			<button type="submit">로그인</button>
		</form>
	</div>

	<script type="text/javascript" src="${path}/js/test.js"></script>
</body>
</html>