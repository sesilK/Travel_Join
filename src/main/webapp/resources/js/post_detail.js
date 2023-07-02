$(function () {

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


    // 마감버튼 일듯?
    // document.getElementById("deadBtn").addEventListener("click", function () {
    //     $.ajax({
    //         url: "/joinDead",	//Controller 요청 주소
    //         type: "POST",	//요청 method
    //         contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
    //         data: JSON.stringify({	//JSON string 으로 변환
    //             planId: planId
    //         }),	//파라미터로 같이 담아서 보낼 것들
    //         success: (data) => {
    //             if (data === 'true') {
    //                 alert('마감 완료');
    //                 window.location.href = "/join_view";
    //             }
    //             if (data === 'false') {
    //                 alert('마감 실패');
    //             }
    //         },	//요청에 대해 성공한 경우 수행할 내용
    //         error: () => {
    //             alert('마감 실행 오류');
    //         }	//요청이 실패,오류난 경우 수행할 내용
    //
    //     });
    //
    // });

    // 삭제버튼 이겠지?
    // document.getElementById("deleteBtn").addEventListener("click", function () {
    //     console.log('삭제버튼누름');
    //     $.ajax({
    //         url: "/joinDelete",	//Controller 요청 주소
    //         type: "POST",	//요청 method
    //         contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
    //         data: JSON.stringify({	//JSON string 으로 변환
    //             planId: planId
    //         }),	//파라미터로 같이 담아서 보낼 것들
    //         success: (data) => {
    //             if (data === 'true') {
    //                 alert('삭제 완료');
    //                 window.location.href = "/join_view";
    //             }
    //             if (data === 'false') {
    //                 alert('삭제 실패');
    //             }
    //         },	//요청에 대해 성공한 경우 수행할 내용
    //         error: () => {
    //             alert('삭제 실행 오류');
    //         }	//요청이 실패,오류난 경우 수행할 내용
    //     });
    // });

});