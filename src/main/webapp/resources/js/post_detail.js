$(function () {

    // 템플릿 슬라이드 관련 설정
    const imgs = document.querySelectorAll('.img-item a');
    const imgBtns = [...imgs];
    let imgId = 1;

    imgBtns.forEach((imgItem) => {
        imgItem.addEventListener('click', (event) => {
            event.preventDefault();
            imgId = imgItem.dataset.id;
            slideImage();
        });
    });

    function slideImage() {
        const displayWidth = document.querySelector('.img-showcase img:first-child').clientWidth;
        document.querySelector('.img-showcase').style.transform = `translateX(${-(imgId - 1) * displayWidth}px)`;
    }

    window.addEventListener('resize', slideImage);


    // planId 전역변수 설정
    const planId = $("body").data("planid");

    // 참가하기 버튼
    $("#btn-join").click(function () {

        $.ajax({
            url: "/joinParty",	//Controller 요청 주소
            type: "POST",	//요청 method
            contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
            data: JSON.stringify({	//JSON string 으로 변환
                planId: planId
            }), //파라미터로 같이 담아서 보낼 것들

            success: (data) => {
                console.log(data);
                if (data == 'true') {
                    alert('신청 완료');
                }
                if (data == 'false') {
                    alert('이미 참여중인 여행입니다');
                }
            },	//요청에 대해 성공한 경우 수행할 내용
            error: () => {
                alert('참가 실행 오류');
            }	//요청이 실패,오류난 경우 수행할 내용

        });
    });

    // 추천하기 버튼
    $("#btn-like").click(function () {
        $.ajax({
            url: "/joinLike",
            type: "POST",	//요청 method
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                planId: planId,
                sort: 'L'
            }),
            success: (data) => {
                if (data == -1) {
                    alert('이미 추천한 글입니다.');
                } else if (data > 0) {
                    let likes = $('#likes'); //추천수 요소
                    likes.text(data); // 추천 수 업데이트
                    alert('추천하였습니다.');
                }
            },
            error: () => {
                alert('추천 실행 오류');
            }

        });
    });

    // 모집 마감
    $("#btn-end").click(function () {
        $.ajax({
            url: "/joinDead",	//Controller 요청 주소
            type: "POST",	//요청 method
            contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
            data: JSON.stringify({	//JSON string 으로 변환
                planId: planId
            }),	//파라미터로 같이 담아서 보낼 것들
            success: (data) => {
                if (data === 'true') {
                    alert('마감 완료');
                    location.href = "/join_view";
                }
                if (data === 'false') {
                    alert('마감 실패');
                }
            },	//요청에 대해 성공한 경우 수행할 내용
            error: () => {
                alert('마감 실행 오류');
            }	//요청이 실패,오류난 경우 수행할 내용

        });
    });


    // 삭제버튼
    $("#btn-del").click(function () {

        $.ajax({
            url: "/joinDelete",	//Controller 요청 주소
            type: "POST",	//요청 method
            contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
            data: JSON.stringify({	//JSON string 으로 변환
                planId: planId
            }),	//파라미터로 같이 담아서 보낼 것들
            success: (data) => {
                if (data === 'true') {
                    alert('마감 완료');
                    location.href = "/join_view";
                }
                if (data === 'false') {
                    alert('마감 실패');
                }
            },	//요청에 대해 성공한 경우 수행할 내용
            error: () => {
                alert('마감 실행 오류');
            }	//요청이 실패,오류난 경우 수행할 내용

        });
    });
});