$(document).ready(function() {

	// write 버튼 클릭 이벤트 처리
	$('body').on('click', 'button#write', function() {
		window.location.href = "/reviewWrite";
	});

	// ${item.title} 클릭 이벤트 처리
	$('body').on('click', 'td.title', function() {
		let reviewId = $(this).data('reviewid');
		$.ajax({
			type: "POST",	//요청 method
			contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
			url: "/reviewViewTitle",
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
				} else if (data === 'not') {
					alert("존재하지 않는 글입니다.");
				}
			},	//요청에 대해 성공한 경우 수행할 내용
			error: () => {
				alert("실행 오류");
			}	//요청이 실패,오류난 경우 수행할 내용
		});
	});
	
});

// data-stars 속성에 따라 rating_star 요소의 너비를 설정하는 JavaScript 코드
document.addEventListener('DOMContentLoaded', function () {
	const ratingStars = document.querySelectorAll('.rating_star');
	ratingStars.forEach(function (star) {
		const stars = parseFloat(star.dataset.stars);
		const widthPercentage = stars * 2 * 10; // 별점을 퍼센트로 변환
		// 인라인 스타일을 사용하여 요소의 너비 설정
		star.style.width = widthPercentage + '%';
	});
});

$(function() {
	$(".review").addClass("is-active");
});