<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<style>
div.centerBox {
	width: 97%;
	height: 50%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
</style>


<div class="centerBox">
	<h3>접근할 수 없는 페이지</h3>
	<p></p>
	<h4><span id="seconds">3</span>초 후 이전페이지로 돌아갑니다.</h4>
</div>


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
        	// console.log(seconds);
            updateCountdown(seconds); //남은시간 변경하는 함수 호출
            setTimeout(() => redirectToReviewList(seconds - 1), 1000); // 1초마다 다시 호출
        }
	}
	
	let fewSeconds = $('#seconds').text();
	
	// n초 뒤에 redirectToOtherPage() 함수 실행
	setTimeout(() => redirectToReviewList(fewSeconds), 1000); // 1000 밀리초 x n번 = n초


</script>