let totalData; //총 데이터 수
let dataPerPage = 10; //한 페이지에 나타낼 글 수
let pageCount = 5; //페이징에 나타낼 페이지 수
let globalCurrentPage = 1; //초기 페이지
let dataList; //표시하려하는 데이터 리스트

$(document).ready(function() {

	loadReviewList(globalCurrentPage);
	
	//검색 버튼 클릭
	$('#searchBtn').click(function() {
		globalCurrentPage = 1;
		loadReviewList(globalCurrentPage);
	});

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
function starsWidth(){
	const ratingStars = document.querySelectorAll('.rating_star');
	ratingStars.forEach(function(star) {
		const stars = parseFloat(star.dataset.stars);
		const widthPercentage = stars * 2 * 10; // 별점을 퍼센트로 변환
		// 인라인 스타일을 사용하여 요소의 너비 설정
		star.style.width = widthPercentage + '%';
	});
};


//현재 페이지(currentPage)와 페이지당 글 개수(dataPerPage) 반영
function displayData(dataList) {
	// console.log(dataList);

	let chartHtml = "";


	for (let i = 0; i < dataList.length; i++ ) {
		chartHtml +=
			'<tr>' +
			'<td class="stars"><span class="rating_box">' +
			'<span class="rating">★★★★★ ' +
			'<span class="rating_star" data-stars="'+ dataList[i].stars +'">★★★★★</span>' +
			'</span></span></td>' + 
            '<td class="title" data-reviewid="'+ dataList[i].reviewId +'">' + 
			dataList[i].destination + " │ " + dataList[i].title;
			if(dataList[i].commentCount > 0){
				chartHtml += '['+dataList[i].commentCount+']';
			}
			chartHtml += 	
			'</td>' +
			'<td>'+ dataList[i].nick +'</td>' +
			'<td>'+ dataList[i].createDate +'</td>' +
			'<td>'+ dataList[i].views +'</td>' +
			'<td>'+ dataList[i].likeCount +'</td>' +
			'</tr>';
	} 
	$("#dataTableBody").html(chartHtml);
}

//페이징 표시 함수  (총 데이터 수, 한 페이지에 나타낼 글 수/페이지 수, 현재 페이지, 데이터리스트)
function paging(totalData, dataPerPage, pageCount, currentPage) {
	// console.log("currentPage : " + currentPage);

	totalPage = Math.ceil(totalData / dataPerPage); //총 페이지 수

	// 총 페이지 수가 페이지 수보다 작은 경우, 페이지 수 조정
	if (totalPage < pageCount) {
		pageCount = totalPage;
	}

	let pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹
	let last = pageGroup * pageCount; //화면에 보여질 마지막 페이지 번호

	// 마지막 페이지 번호를 총 페이지 수를 넘지 않도록 조정
	if (last > totalPage) {
		last = totalPage;
	}

	let first = last - (pageCount - 1); //화면에 보여질 첫번째 페이지 번호
	let next = last + 1;
	let prev = first - 1;

	let pageHtml = "";

	if (prev > 0) {
		pageHtml += "<li><a href='#' id='prev'> 이전 </a></li>";
	}

	//페이징 번호 표시 
	for (var i = first; i <= last; i++) {
		if (currentPage == i) {
			pageHtml +=
				"<li class='on'><a href='#' id='" + i + "'>" + i + "</a></li>";
		} else {
			pageHtml += "<li><a href='#' id='" + i + "'>" + i + "</a></li>";
		}
	}
	
	// 다음 페이지 번호가 총 페이지 수를 넘지 않도록 조정
	if (next > totalPage) {
    	next = totalPage; //next = 0;
	}

	if (last < totalPage) {
		pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
	}

	$("#pagingul").html(pageHtml);
	let displayCount = "";
	displayCount = "현재 1 - " + totalPage + " 페이지 / " + totalData + "건";
	$("#displayCount").text(displayCount);


	//페이징 번호 클릭 이벤트 
	$("#pagingul li a").click(function() {
		let $id = $(this).attr("id");
		currentPage = $(this).text();

		if ($id == "next") currentPage = next;
		if ($id == "prev") currentPage = prev;

		//전역변수에 선택한 페이지 번호를 담기
		globalCurrentPage = currentPage;
		//페이지 다시 불러오기
		loadReviewList(currentPage);
	});
}

//페이지 조회 및 검색
function loadReviewList(page){
	
	// 선택된 옵션 값과 입력된 키워드 가져오기
    let searchType = $('#searchType').val();
    let searchCondition = $('#searchCondition').val();
    let keyword = $('#keyword').val();
    
    //총 데이터 수 찾기
	$.ajax({ // ajax로 데이터 가져오기
		method: "POST",
		url: "/reviewTotalCount",
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({
		    destination: searchType,
		    planInfo: searchCondition,
		    content: keyword
		}),
		success: function(data) {
			totalData = data; //총 데이터 수
			//페이지네이션
			$.ajax({ // ajax로 데이터 가져오기
				method: "POST",
				url: "/review",
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify({
				    destination: searchType,
				    planInfo: searchCondition,
				    content: keyword,
				    page: page,
				    nick: dataPerPage
				}),
				success: function(dataList) {
					//페이지네이션과 데이터 표시를 처리하는 함수 호출
                    displayPageAndData(page, dataPerPage, dataList, totalData);
					
				}
			});
		}
	});
}

//페이지와 데이터를 표시하는 함수
function displayPageAndData(currentPage, dataPerPage, dataList, totalData) {
    displayData(dataList); //글 목록 표시 호출 (테이블 생성)
    starsWidth(); //별점 너비 넣기
    paging(totalData, dataPerPage, pageCount, currentPage); //페이징 표시 호출
}

// 엔터 키를 눌렀을 때 처리할 함수를 정의합니다.
function handleEnterKeyPress(event) {
  if (event.key === "Enter") { //누른 키가 엔터일 경우에
    event.preventDefault(); // 엔터키 기본동작(폼제출) 막기
    document.getElementById("searchBtn").click(); //검색버튼 클릭하기
  }
}

//엔터 키 이벤트를 감지할 요소를 선택
document.body.addEventListener("keypress", handleEnterKeyPress);

$(function() {
	$(".review").addClass("is-active");
});