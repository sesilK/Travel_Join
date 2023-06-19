<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>reviewWrite</h1>
	
	<form action="" method="POST" onsubmit="return checkForm();">
		여행 <select name="planId">
				<option selected>선택해주세요.</option>
				<option value="여행1">여행1</option>
				<option value="여행2">여행2</option>
				<option value="여행3">여행3</option>
			</select><br/>
		별점 <select name="stars">
				<option selected>선택해주세요.</option>
					<c:forEach var="i" begin="0" end="10" step="1">
				    	<option value="${10/2.0 - i/2.0}">${10/2.0 - i/2.0}</option>
					</c:forEach>
			</select><br/>
		제목 <input type="text" name="title"/><br/>
		내용 <textarea name="content"></textarea><br/>
		<button type="submit">등록하기</button><a href="reviewBbs"><button type="button">돌아가기</button></a>
	</form>
	
	<script>
		function checkForm() {
	    	var planId = document.getElementsByName("planId")[0].value;
	    	var stars = document.getElementsByName("stars")[0].value;
	    	var title = document.getElementsByName("title")[0].value;
	    	var content = document.getElementsByName("content")[0].value;
	
	    	if (planId === "" || stars === "" || title === "" || content === "") {
	    		alert("모든 필드를 입력해주세요.");
				return false;
	    	}
	    	return true;
	  }
	</script>
</body>
</html>