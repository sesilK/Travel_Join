<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../resources/css/post_detail.css" />

<title>Insert title here</title>
</head>

<body>
    <article class="product">

        <section>

            <img src="https://slp-statics.astockcdn.net/static_assets/staging/22spring/kr/illustrations/categories/card-6.jpg?width=580&format=webp"
                alt="게시글작성시 이미지">
        </section>


        <section>

            <div>
                <h4>${item.userId}</h4>
                <span>
                    <h5>viewcount</h5>
                    <img src="data:image/svg+xml;charset=utf-8;base64,PHN2ZyB3aWR0aD0iNDQiIGhlaWdodD0iNDQiIHZpZXdCb3g9IjAgMCAxNiAxNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiBpZD0iaWNvbi1sb3ZlLTEiIGZpbGw9IiNjODBhMWUiPjxwYXRoIGQ9Im04IDE0Ljg4LS45OC0uOThDMy4zOCAxMC42OCAxIDguNTEgMSA1Ljg1IDEgMy42OCAyLjY4IDIgNC44NSAyIDYuMDQgMiA3LjIzIDIuNTYgOCAzLjQ3IDguNzcgMi41NiA5Ljk2IDIgMTEuMTUgMiAxMy4zMiAyIDE1IDMuNjggMTUgNS44NWMwIDIuNjYtMi4zOCA0LjgzLTYuMDIgOC4wNWwtLjk4Ljk4WiIgZmlsbD0iI2M4MGExZSIgZmlsbC1ydWxlPSJub256ZXJvIj48L3BhdGg+PC9zdmc+"
                        alt="">
                </span>
            </div>

            <h4>${item.title}</h4>


            <div class="shippinginfo">
                <p>${item.content} </p>
                
            </div>

 
            </div>

        </section>

    </article>
    


    <article class="productinfo">

        <h4>필수 정보</h4>

        <div>
            <div class="productinfo_01">
                <ul>
                    <li>여행기간</li>
                    <li>여행지</li>
                    <li>들어갈 것</li>


                </ul>
                <ul>
                    <li>${item.startDay.substring(0,10)} ~ ${item.endDay.substring(0,10)}</li>
                    <li>어디를 갈 건지</li>
                    <li>*상세페이지 참조</li>

                </ul>
            </div>

    </article>

				<form action="/joinParty" method="post">
						<input type="hidden" name="planId" value="${item.planId}" /> 
						<input type="hidden" name="userId" value="현재 접속 중인 아이디" /> 
						<input type="submit" value="동행 신청하기" />
					</form>
<!-- 
<script type="text/javascript">
    
    document.getElementById("joinButton").addEventListener("click", function() {
        $.ajax({
            type: "POST",	// 요청 method
            contentType: "application/json; charset=utf-8",	
            url: "/joinParty_process",	// 요청을 보낼 경로
            data: JSON.stringify({	// 전송할 데이터를 JSON 형식으로 변환
                title: title,
            
            }),	// 전송할 데이터
            success: (data) => {	// 요청이 성공한 경우 수행할 내용
                window.location.href = "/detail";	// detail 페이지로 리다이렉트
                return true;
            },
            error: () => {	// 요청이 실패한 경우 수행할 내용
                alert('실행 오류');
            }
        });
    });
</script> -->
	





</body>

</html>