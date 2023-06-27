<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.chat {
	position: fixed;
	bottom: 10px; 
	right : 50px;
	z-index: 9999; 
}
.chat>img {
width: 150px;
}
</style>
<body>
	<%@ include file="header2.jsp"%>
	<div class="main">
		<h1>메인</h1>
	</div>

	</div>
	<!-- 컨테이너 막는거 -->

	<div class="chat">
		<img src="/images/chat.png">
	</div>
</body>
</html>