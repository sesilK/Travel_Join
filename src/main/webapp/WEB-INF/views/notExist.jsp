<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- <link href="/css/reviewView.css" rel="stylesheet" type="text/css"/> -->
<%@ include file="header.jsp" %>

	<h3>접근할 수 없는 페이지</h3>
	<h4><span id="seconds">5</span>초 후 이전페이지로 돌아갑니다.</h4>
	<a href="review"><button>바로 이동</button></a>
	


<%@ include file="footer.jsp" %>
<script>

	// 남은 시간을 업데이트하는 함수
	function updateCountdown(seconds) {
	    document.getElementById('seconds').innerText = seconds;
	}

	//페이지 이동하는 함수
	function redirectToReviewList(seconds) {
		if (seconds <= 0) {	//남은시간 0초 되면 이동
			window.history.back();	//이전페이지로 이동
        } else {	//남은시간 0초 되기 전에는 
        	console.log(seconds);
            updateCountdown(seconds); //남은시간 변경하는 함수 호출
            setTimeout(() => redirectToReviewList(seconds - 1), 1000); // 1초마다 다시 호출
        }
	}
	
	// 5초 뒤에 redirectToOtherPage() 함수 실행
	setTimeout(() => redirectToReviewList(5), 1000); //15000 밀리초 x 5번 = 5초


</script>