let reviewId = $('#reviewId').data('reviewid'); // 글 번호
let userId = $('#userId').data('userid'); // 글 작성자
let sessionId = $('#sessionId').data('sessionid'); // 로그인 아이디
let commentMaxByte = 300; //댓글 입력제한 300 Byte

console.log(reviewId);
console.log(userId);
console.log(sessionId);

$(document).ready(function() {

	// 신고 버튼 클릭 이벤트 처리
	$('#report').click(function() {

		$.ajax({
			type: "POST",	//요청 method
			contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
			url: "/reviewMark",	//어디 경로로 요청할건지 (Restful Api 서버 요청 주소)
			data: JSON.stringify({	//객체를 -> JSON string 으로 변환
				userId: sessionId,
				reviewId: reviewId,
				sort: 'R'
			}),	//파라미터로 같이 담아서 보낼 것들
			success: (data) => {
				if (data == -1) {
					alert('이미 신고한 글입니다.');
				} else if (data == -2){
					alert('로그인 아이디가 없습니다.');
					window.location.href = "/login";
				} else if (data > 0){
					alert('신고하였습니다.');
				}
			},	//요청에 대해 성공한 경우 수행할 내용
			error: () => {
				alert('실행 오류');
			}	//요청이 실패,오류난 경우 수행할 내용
		});

	});

	// 추천 버튼 클릭 이벤트 처리
	$('#like').click(function() {

		$.ajax({
			type: "POST",	//요청 method
			contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
			url: "/reviewMark",	//어디 경로로 요청할건지 (Restful Api 서버 요청 주소)
			data: JSON.stringify({	//객체를 -> JSON string 으로 변환
				userId: sessionId,
				reviewId: reviewId,
				sort: 'L'
			}),	//파라미터로 같이 담아서 보낼 것들
			success: (data) => {
				if (data == -1) {
					alert('이미 추천한 글입니다.');
				} else if (data == -2){
					alert('로그인 아이디가 없습니다.');
					window.location.href = "/login";
				} else if (data > 0){
					let likeCount = $('#likeCount'); //추천수 요소
					likeCount.text(data); // 추천 수 업데이트
					alert('추천하였습니다.');
				}
			},	//요청에 대해 성공한 경우 수행할 내용
			error: () => {
				alert('실행 오류');
			}	//요청이 실패,오류난 경우 수행할 내용
		});

	});

	// 글 수정 버튼 클릭 이벤트 처리
	$('#modifyBtn').click(function() {
		window.location.href = "/reviewModify?reviewId=" + reviewId;
	});

	// 글 삭제 버튼 클릭 이벤트 처리
	$('#deleteBtn').click(function() {

		if (confirm('삭제하시겠습니까?')) {
			$.ajax({
				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: "/deleteReview",
				data: JSON.stringify({
					userId: userId,
					reviewId: reviewId
				}),
				success: function(data) {
					if (data === 'true') {
						alert('게시물이 삭제되었습니다.');
						window.location.href = "/review";
					} else if (data === 'idNull') {
						alert('삭제 권한이 없습니다.(로그인 아이디 없음)');
						window.location.href = "/login";
					} else {
						alert('삭제 권한이 없습니다.');
					}
				},
				error: function() {
					alert('게시물 삭제 실행 오류');
				}
			});
		}
	});

	// 댓글 등록 버튼 클릭 이벤트 처리
	$('#commentBtn').click(function() {

		let commentEl = $('input[name="comment"]');	//댓글 입력 요소
		let content = commentEl.val(); //댓글 내용

		if (content != '') {
			$.ajax({
				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: "/comment",
				data: JSON.stringify({
					reviewId: reviewId,
					content: content,
					commentLv: 1,
					parentCommentId: 0
				}),
				success: function(data) {
					console.log(data);
					if (data === '') {
						alert('로그인 아이디가 없습니다.');
						window.location.href = "/login";
					} else {
						renderComments(data);
					}
				},
				error: function() {
					alert('댓글 등록 실행 오류');
				}
			});
		} else {
			alert('내용을 입력하세요');
			commentEl.focus();
		}
	});

	// 답글 input 열기
	$('#commentList').on('click', '.replyBtn', function() {
		// 다른 버튼들 숨김 처리
		$('#commentList button').hide();

		let commentRow = $(this).closest('tr');

		// 답글 입력 input 추가
		let replyInput = '<tr id="replyRow">' +
			'<td colspan="4">' +
			'<input type="text" name="replyContent">' +
			'<button id="replyConfirmBtn" type="button">등록</button>' +
			'<button id="replyCancelBtn" type="button">취소</button>' +
			'</td>' +
			'</tr>';
		commentRow.after(replyInput);

		//답글 글자수 제한
		$('input[name="replyContent"]').on('input', function() {
			let content = $(this).val();
			$(this).val(limitByte(content));
		});
	});

	// 답글 취소버튼 클릭 이벤트 처리
	$('#commentList').on('click', '#replyCancelBtn', function() {
		// 원래의 버튼들을 다시 보이게 처리
		$('#commentList button').show();

		// 답글 입력 input 제거
		let replyRow = $(this).closest('tr#replyRow');
		replyRow.remove();
	});

	// 답글 등록버튼 클릭 이벤트 처리
	$('#commentList').on('click', '#replyConfirmBtn', function() {
		// 원래의 버튼들을 다시 보이게 처리
		$('#commentList button').show();

		let replyEl = $('input[name="replyContent"]');	//답글 입력 요소
		let content = replyEl.val(); //답글 내용
		let commentRow = $(this).closest('tr').prev('tr');
		let parentId = commentRow.attr('data-comment-id'); //부모댓글번호
		let parentLv = parseInt(commentRow.attr('data-comment-lv'), 10); //부모댓글레벨(10진수 표현)

		if (content != '') {
			$.ajax({
				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: "/comment",
				data: JSON.stringify({
					reviewId: reviewId,
					content: content,
					commentLv: parentLv + 1,
					parentCommentId: parentId
				}),
				success: function(data) {
					if (data === '') {
						alert('로그인 아이디가 없습니다.');
						window.location.href = "/login";
					} else {
						renderComments(data);
					}
				},
				error: function() {
					alert('답글글 등록 실행 오류');
				}
			});
		} else {
			alert('내용을 입력하세요');
			replyEl.focus();
		}

	});

	// 댓글 수정 input 열기
	$('#commentList').on('click', '.modifyBtn', function() {
		// 다른 버튼들 숨김 처리
		$('#commentList button').hide();

		let commentRow = $(this).closest('tr');
		let contentDateEl = commentRow.find('td:first-child').find('span:first-child')
		let content = contentDateEl.find('span:first-child').text();

		//수정할 댓글을 입력요소로 변경
		let modifyCommentForm = `
			    	<span>
				    	<input name="modifiedComment" value="` + content + `">
				    	<button type="button" id="confirmBtn">확인</button>
				    	<button type="button" id="cancelBtn">취소</button>
			    	</span>
			    `;
		contentDateEl.replaceWith(modifyCommentForm);

		//수정 글자수 제한
		$('input[name="modifiedComment"]').on('input', function() {
			let content = $(this).val();
			$(this).val(limitByte(content));
		});
	});

	// 댓글 수정취소 버튼 클릭 이벤트 처리
	$('#commentList').on('click', '#cancelBtn', function() {
		// 원래의 버튼들을 다시 보이게 처리
		$('#commentList button').show();

		let commentRow = $(this).closest('tr');
		let commentTd = commentRow.find('td:first-child');
		let content = commentTd.data('content');		//댓글내용(수정전)
		let createDate = commentTd.data('createdate');	//작성일
		let updateDate = commentTd.data('updatedate');	//수정일
		let commentLv = commentRow.data('comment-lv');	//댓글레벨
		let modifyCommentForm = commentTd.find('span:first-child');	//수정모드 요소

		// 원래의 댓글 내용으로 변경
		let contentDateEl = $('<span></span>').append($('<span></span>').text(content));
		if (updateDate == '' || updateDate == null) {
			contentDateEl.append(' (' + createDate + ')');
		} else {
			contentDateEl.append(' (' + updateDate + ' 수정)');
		}
		if (commentLv < 3) {
			contentDateEl.append('<button class="replyBtn" type="button">답글</button>');
		}
		modifyCommentForm.replaceWith(contentDateEl);
	});

	// 댓글 수정완료 버튼 클릭 이벤트 처리
	$('#commentList').on('click', '#confirmBtn', function() {

		let commentRow = $(this).closest('tr');
		let commentId = commentRow.attr('data-comment-id');
		let commentEl = $('input[name="modifiedComment"]');
		let commentTd = commentRow.find('td:first-child');
		let before = commentTd.data('content');		//댓글내용(수정전)
		let content = commentEl.val(); //수정한 내용

		if (before == content) {
			commentEl.focus();
		} else if (content != '') {
			if (confirm('댓글 수정하시겠습니까?')) {
				$.ajax({
					type: "POST",
					contentType: "application/json; charset=utf-8",
					url: "/updateComment",
					data: JSON.stringify({
						reviewId: reviewId,
						commentId: commentId,
						content: content
					}),
					success: function(data) {
					if (data === '') {
						alert('로그인 아이디가 없습니다.');
						window.location.href = "/login";
					} else {
						renderComments(data);
					}
				},
					error: function() {
						alert('댓글 수정 실행 오류');
					}
				});
			}
		} else {
			alert('내용을 입력하세요');
			commentEl.focus();
		}

	});

	// 댓글삭제 버튼 클릭 이벤트 처리
	$('#commentList').on('click', '.deleteBtn', function() {
		let commentRow = $(this).closest('tr');
		let commentId = commentRow.attr('data-comment-id');

		if (confirm('삭제하시겠습니까?')) {
			$.ajax({
				type: 'POST',
				contentType: "application/json; charset=utf-8",
				url: '/deleteComment',
				data: JSON.stringify({
					commentId: commentId,
					reviewId: reviewId
				}),
				success: function(data) {
			        if (data.true) {
						renderComments(data.commentList);
			        } else if (data.idNull) {
						alert('삭제 권한이 없습니다.(로그인 아이디 없음)');
						window.location.href = "/login";
			        } else if (data.false) {
						alert('삭제 권한이 없습니다.');
			        }
				},
				error: function() {
					alert('댓글 삭제 실행 오류');
				}
			});
		}
	});

	//댓글 글자수 제한
	$('input[name="comment"]').on('input', function() {
		let content = $(this).val();
		$(this).val(limitByte(content));
	});

});

//댓글 글자수 제한 함수
function limitByte(content) {
	let charLength = content.length	//글자 길이
	let byteLength = calculateByteLength(content); //바이트 길이
	if (byteLength > commentMaxByte) {
		let trimmedValue = content.substring(0, charLength - 1); // 입력값을 현재 글자까지만 잘라냄
		return trimmedValue; // 입력값을 잘라낸 값으로 설정하여 길이를 제한함
	}
	return content;
}

//글자수 byte 변환 함수
function calculateByteLength(str) {
	let byteLength = 0;
	for (let i = 0; i < str.length; i++) {
		let charCode = str.charCodeAt(i);
		if (charCode <= 0x007F) { // 영어, 숫자, 특수문자는 1바이트로 처리
			byteLength += 1;
		} else { // 한글 및 기타 유니코드 문자는 3바이트로 처리
			byteLength += 3;
		}
	}
	return byteLength;
}

// 댓글 리스트를 렌더링
function renderComments(commentList) {
	let commentCount = 0; // 댓글 수 세기
	let html = '';

	for (let i = 0; i < commentList.length; i++) {
		let comment = commentList[i];

		html += '<tr data-comment-id="' + comment.commentId
			+ '" data-comment-lv="' + comment.commentLv + '">';

		if (comment.deleteAt == 'N') {// 삭제되지 않은 댓글
			commentCount += 1; // 댓글 수에 포함
			html += '<td data-content="' + comment.content +
				'" data-createdate="' + comment.createDate +
				'" data-updatedate="' + comment.updateDate +
				'">';
			if (comment.commentLv > 1) {
				for (let i = 1; i < comment.commentLv; i++) {
					html += '&emsp;';
				}
			}
			html += ' ' + comment.nick;
			if (comment.userId === userId) {
				html += '(글쓴이)';
			}
			html += ' | <span><span>' + comment.content + '</span>';
			if (comment.updateDate == null) {
				html += ' (' + comment.createDate + ') ';
			} else {
				html += ' (' + comment.updateDate + ' 수정) ';
			}
			if (comment.commentLv < 3) {
				html += '<button class="replyBtn" type="button">답글</button>';
			}
			html += '</span></td>';
			if (comment.userId == sessionId) {
				html += '<td><button class="modifyBtn" type="button">수정</button></td>';
				html += '<td><button class="deleteBtn" type="button">삭제</button></td>';
			}
		} else if (comment.deleteAt == 'Y') {// 삭제된 댓글
			html += '<td>';
			if (comment.commentLv > 1) {
				for (let i = 1; i < comment.commentLv; i++) {
					html += '&emsp;';
				}
			}
			html += '삭제된 댓글입니다.</td>';
		}

		html += '</tr>';
	}
	
	// 생성한 HTML을 특정 요소에 추가
	$('#commentList').html(html);
	$('input[name="comment"]').val('');

	// 댓글 수 업데이트
	let commentCountEl = $('.commentCount'); // 댓글 수 요소
	commentCountEl.text(commentCount); // 댓글 수 업데이트
	
	
}

$(function() {
	$(".review").addClass("is-active");
});