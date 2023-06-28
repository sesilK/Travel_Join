$(document).ready(function() {

	// ${item.title} 클릭 이벤트 처리
	$('body').on('click', 'td.title', function() {
		let reviewId = $(this).data('reviewid');
		$.ajax({
			type: "POST",	//요청 method
			contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
			url: "/reviewView",	//어디 경로로 요청할건지 (Restful Api 서버 요청 주소)
			data: JSON.stringify({	//객체를 -> JSON string 으로 변환
				reviewId: reviewId
			}),	//파라미터로 같이 담아서 보낼 것들
			success: (data) => {
				if (data === 'view') {
					window.location.href = "/reviewView?reviewId=" + reviewId;
				} else if (data === 'deleted') {
					alert("삭제된 글입니다.");
				} else if (data === 'reported') {
					alert("검토 중인 글입니다.");
				}
			},	//요청에 대해 성공한 경우 수행할 내용
			error: () => {
				alert('실행 오류');
			}	//요청이 실패,오류난 경우 수행할 내용
		});
	});

});

$(function() {
	$(".review").addClass("is-active");
});