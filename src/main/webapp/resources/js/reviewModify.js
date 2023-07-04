const rating_input = document.querySelector('.rating input');
const rating_star = document.querySelector('.rating_star');

// 별점 드래그 할 때
rating_input.addEventListener('input', () => {
  rating_star.style.width = `${rating_input.value * 10}%`;
});

// 마우스 움직일 때
$('.rating_box').on('mousemove', function(event) {
    const mouseX = event.clientX; // 마우스의 X 좌표
    const starX = rating_star.getBoundingClientRect().left; // 박스의 X 좌표

    // 마우스 위치까지만 width 조절
    const newWidth = mouseX - starX;
    rating_star.style.width = `${newWidth}px`;
    rating_star.style.color = `#ff9307`;
});

// 마우스를 요소 밖으로 빼면 복원
$('.rating_box').on('mouseout', function() {
    const rating_input = document.querySelector('.rating input');
    rating_star.style.width = `${rating_input.value * 10}%`;
    rating_star.style.color = `#ffc107`;
});

// 마우스를 클릭한 경우
$('.rating_box').on('mousedown', function() {
	$('.rating').css('font-size', '30.1px');
});
// 마우스를 놓은 경우
$('.rating_box').on('mouseup', function() {
	$('.rating').css('font-size', '30px');
});

let titleMaxByte = 300; //제목 입력제한 300 Byte
let contentMaxByte = 3000; //내용 입력제한 3000 Byte
let reviewId = $('#planId').data('reviewid');

$(document).ready(function() {

	//썸머노트 불러오기
	$('#summernote').summernote({
		//width: 800, // set editor width
		height: $(window).height()* 0.5, // set editor height
		//minHeight: null, // set minimum height of editor
		//maxHeight: null, // set maximum height of editor
		focus: false, // focus  여부 설정 (false -> focus)
		lang: 'ko-KR', // 기본 메뉴언어 US->KR로 변경
		placeholder: '', //placeholder 설정
		callbacks: {
			onImageUpload: function(files) { //이미지 첨부
				for (let i = files.length - 1; i >= 0; i--) { // 다중 업로드
					let content = $('div[role="textbox"]')[0].innerHTML;
					let byteLength = calculateByteLength(content); // 바이트 길이 계산
					if (byteLength <= contentMaxByte) { // 바이트 제한 이내인 경우
						uploadReviewImageFile(files[i], this); //업로드
					} else { // 바이트 제한 걸리면
						alert("용량을 초과하여 업로드할 수 없습니다.");
						break;
					}
				}
			},
			onMediaDelete: function($target) { //이미지 삭제
				let imageName = $target.attr('src').split('/').pop()
				deleteReviewImageFile(imageName)
			},
			onKeydown: function(e) {
				limitByte(e);
			},
			onPaste: function(e) {
				e.preventDefault(); // 붙여넣기 막음
				alert('붙여넣기 기능을 지원하지 않습니다.');
			}
		}
	});

	//$('.note-editor').width($(window).width()*0.6);
	//$('.note-editor').height($(window).height()* 0.6);

	//내용 글자수 제한 함수
	function limitByte(e) {
		let content = e.currentTarget.innerHTML;
		let byteLength = calculateByteLength(content); //바이트 길이

		console.log(byteLength);

		if (byteLength >= contentMaxByte) { // 입력된 텍스트의 바이트가 maxByte를 초과할 경우
			let keyCode = e.keyCode || e.which;
			if (!(keyCode == 8 || keyCode == 37 || keyCode == 38 ||
				keyCode == 39 || keyCode == 40)) { // 백스페이스나 방향키가 아닌 경우에만
				e.preventDefault();	//키 입력 막음
				return false;
			}
		}
		return true;
	}

	//수정할 글 내용 불러오기
	$.ajax({
		url: 'reviewLoad', // 서버 엔드포인트 URL을 지정해야 합니다.
		method: 'POST',
		contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
		data: JSON.stringify({	//객체를 -> JSON string 으로 변환
			reviewId: reviewId
		}),	//파라미터로 같이 담아서 보낼 것들
		success: function(response) {
			let planId = response.planId;
			let stars = response.stars.toFixed(1);
			let title = response.title;
			let content = response.content;
			$('select[name="planId"]').val(planId);
			$('input[id="stars"]').val(stars*2);
			$('input[name="title"]').val(title);
			$('#summernote').summernote('code', content);
			
			// rating_star 클래스에 추가할 스타일 속성
			let styles = {
			    width: ""+stars*2+"0%" // 원하는 너비로 설정
			};
			addStyleToClass("rating_star", styles);
		},
		error: function() {
			alert("내용을 불러오지 못했습니다.");
		}
	});

	
	//제목 글자수 제한 함수
	$('input[name="title"]').on('input', function() {
		let title = $(this).val();
		let charLength = title.length	//글자 길이
		let byteLength = calculateByteLength(title); //바이트 길이

		if (byteLength > titleMaxByte) {
			let trimmedValue = title.substring(0, charLength - 1); // 입력값을 현재 글자까지만 잘라냄
			console.log(trimmedValue);
			$(this).val(trimmedValue); // 입력값을 잘라낸 값으로 설정하여 길이를 제한함
		}
	});

});

// 특정 클래스에 스타일 속성을 추가하는 함수
function addStyleToClass(className, styles) {
    let elements = document.getElementsByClassName(className);
    for (let i = 0; i < elements.length; i++) {
        let element = elements[i];
        for (let property in styles) {
            if (styles.hasOwnProperty(property)) {
                element.style[property] = styles[property];
            }
        }
    }
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

// 텍스트를 제한된 바이트 수로 자르는 함수
function trimTextToByteLength(text, maxBytes) {
	let trimmedText = '';
	let byteLength = 0;

	for (let i = 0; i < text.length; i++) {
		let charCode = text.charCodeAt(i);
		let charByteLength = 0;
		if (charCode <= 0x007F) { // 영어, 숫자, 특수문자는 1바이트로 처리
			charByteLength += 1;
		} else { // 한글 및 기타 유니코드 문자는 3바이트로 처리
			charByteLength += 3;
		}
		if (byteLength + charByteLength >= maxBytes) {
			break;
		}
		trimmedText += text.charAt(i);
		byteLength += charByteLength;
	}

	return trimmedText;
}

//파일 업로드 함수
function uploadReviewImageFile(file, el) {
	let formData = new FormData();
	formData.append("file", file);
	$.ajax({
		data: formData,
		type: "POST",
		url: "/uploadReviewImageFile",	//Controller 요청 주소
		contentType: false,
		enctype: 'multipart/form-data',
		processData: false,
		success: (data) => {
			if(data == 'idNull'){ //등록 실패

			} else {
				$(el).summernote('editor.insertImage', data.url);
			}
		}
	});
}

//파일 삭제 함수
function deleteReviewImageFile(imageName) {
	let formData = new FormData()
	formData.append('file', imageName)
	$.ajax({
		data: formData,
		type: 'POST',
		url: "/deleteReviewImageFile",
		contentType: false,
		enctype: 'multipart/form-data',
		processData: false,
		success: (data) => {
			if(data == 'idNull'){ //등록 실패

			} else {

			}
		},	//요청에 대해 성공한 경우 수행할 내용
		error: () => {
			alert('실행 오류');
		}	//요청이 실패,오류난 경우 수행할 내용
	})
}

document.getElementById("modifyBtn").addEventListener("click", function() { //수정버튼 클릭시

	let planId = $('select[name="planId"]').val();
	let stars = $('input[id="stars"]').val();
	let title = $('input[name="title"]').val();
	let content = $('div[role="textbox"]')[0].innerHTML;
	let contentByte = calculateByteLength(content);

	if (contentByte > contentMaxByte) {
		alert("입력 가능한 글자 수를 초과하였습니다.");
		return false;
	} else if (planId === "0") {
		alert("여행을 선택해주세요.");
		return false;
	} else if (title === "") {
		alert("제목을 입력해주세요.");
		return false;
	} else if (content === "") {
		alert("내용을 입력해주세요.");
		return false;
	} else if (confirm('수정하시겠습니까?')) {

		// 내용에서 이미지 태그들 추출하여 파일명을 리스트로 만들기
		let imageFileNameList = [];
		$(content).find('img').each(function() {
			let imageUrl = $(this).attr('src');
			let fileName = imageUrl.split('/').pop();
			imageFileNameList.push(fileName);
		});

		//폼 전송
		$.ajax({
			type: "POST",	//요청 method
			contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
			url: "/reviewModify", //어디 경로로 요청할건지
			data: JSON.stringify({	//객체를 -> JSON string 으로 변환
				reviewId: reviewId,
				planId: planId,
				stars: stars/2.0,
				title: title,
				content: content,
				imageFileNameList: imageFileNameList
			}),	//파라미터로 같이 담아서 보낼 것들
			success: (data) => {
				if(data == 'idNull'){ //등록 실패
					alert('로그인 아이디가 없습니다.');
					window.location.href = "/login";
				} else {
					window.location.href = "/reviewView?reviewId=" + data;
				}
				//return true;
			},	//요청에 대해 성공한 경우 수행할 내용
			error: () => {
				alert('실행 오류');
			}	//요청이 실패,오류난 경우 수행할 내용

		});

	}

});
$(function() {
	$(".review").addClass("is-active");
});