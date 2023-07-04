<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

	<link href="/css/join_making.css" rel="stylesheet" type="text/css"/>
   
<div class="making-container">

		<div class="makingList">
		
			제 목 <input  type="text" name="title"><br/>
				
			구분 
		<div class ="flex">
			<input  type="radio" checked="checked" value="0" name="planType"> 국내 
			&nbsp;&nbsp;<input type="radio" value="1" name="planType"> 해외 <br/>
		</div>
				
			여 행 지<input  type="text" name="destination"><br/>
			
			여행 기간 
		<div class ="flex">
			<input type="date" name="startDay"> ~ 
			<input type="date" name="endDay"><br/>
		</div>
				
			인 원 수 <input type="number" min="1" max="100" name="personnel">
			마 감 일 <input type="date" name="finishDate">
			<br><br><br>

			<textarea id="summernote" name="content"></textarea>
			<button class="makingBtn" type="button" id="submitBtn">글올리기</button>
		<br><br><br><br><br>
		</div>
		
</div>


<%@ include file="footer.jsp" %>
<script src="/js/join_making.js"></script>