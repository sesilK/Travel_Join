<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/css/header.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="sidebar">
			<div class="side-title">
				<h3>MENU</h3>
			</div>
			<ul>
				<li><a href="#">여행모집</a></li>
				<li><a href="#">여행리뷰</a></li>
				<li><a href="#">공구</a></li>
				<li><a href="#">공지</a></li>
			</ul>
		</div>

		<div class="header">
			<c:if test="${sessionScope.userId == null}">
				<a href="/login">로그인</a>
				<a href="/register">회원가입</a>
			</c:if>
			<c:if test="${sessionScope.userId != null}">
				${sessionScope.userId}님
				<a href="/mypage">마이페이지</a>
				<a href="/logout">로그아웃</a>
			</c:if>
		</div>
