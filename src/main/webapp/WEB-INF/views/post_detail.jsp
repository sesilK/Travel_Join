<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../resources/css/post_detail.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<title>Insert title here</title>
</head>

<body>
    <article class="product">

       <!--  <section>

            <img src="https://slp-statics.astockcdn.net/static_assets/staging/22spring/kr/illustrations/categories/card-6.jpg?width=580&format=webp"
                alt="게시글작성시 이미지">
        </section> -->


        <section>

            <div>
                <h4>${item.userId}</h4>
                <span>
                    <h5>${item.views}</h5>
                </span>
                    <img src="data:image/svg+xml;charset=utf-8;base64,PHN2ZyB3aWR0aD0iNDQiIGhlaWdodD0iNDQiIHZpZXdCb3g9IjAgMCAxNiAxNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiBpZD0iaWNvbi1sb3ZlLTEiIGZpbGw9IiNjODBhMWUiPjxwYXRoIGQ9Im04IDE0Ljg4LS45OC0uOThDMy4zOCAxMC42OCAxIDguNTEgMSA1Ljg1IDEgMy42OCAyLjY4IDIgNC44NSAyIDYuMDQgMiA3LjIzIDIuNTYgOCAzLjQ3IDguNzcgMi41NiA5Ljk2IDIgMTEuMTUgMiAxMy4zMiAyIDE1IDMuNjggMTUgNS44NWMwIDIuNjYtMi4zOCA0LjgzLTYuMDIgOC4wNWwtLjk4Ljk4WiIgZmlsbD0iI2M4MGExZSIgZmlsbC1ydWxlPSJub256ZXJvIj48L3BhdGg+PC9zdmc+"
                        alt="">
            </div>

            <h4>${item.title}</h4>


            <div class="shippinginfo">
                <p>${item.content} </p>
                

            </div>

        </section>

    </article>
    


    <article class="productinfo">

        <h4>필수 정보</h4>

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

<%
session.setAttribute("userId", "admin");
%>

				<%-- <form action="/joinParty" method="post">
						<input type="hidden" name="planId" value="${item.planId}" /> 
						<input type="hidden" name="userId" value="현재 접속 중인 아이디" /> 
						<input type="submit" value="동행 신청하기" />
					</form> --%>
					<%-- <input type="hidden" name="planId" value="${item.planId}" />  --%> 
					
					<c:if test="${CurrPersonnel < item.personnel && item.planState == 0}">
						<button type="button" id="submitBtn">신청하기</button>
						<c:if test="${sessionScope.userId == item.userId}">
							<button type="button" id="deadBtn">모집마감</button>
						</c:if>
					</c:if>
					<c:if test="${CurrPersonnel >= item.personnel || item.planState == 1}">
						<c:if test="${sessionScope.userId == item.userId}">
							<button type="button" id="deleteBtn">삭제하기</button>
							<button type="button" id="Btn">그냥누름</button>
						</c:if>
						<span>모집 종료 되었습니다.</span>
					</c:if>
					
					<script type="text/javascript">

						let planId = ${item.planId};
					
					document.getElementById("submitBtn").addEventListener("click", function() {
						$.ajax({
							url : "/joinParty",	//Controller 요청 주소
							type: "POST",	//요청 method
							contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
							data : JSON.stringify({	//JSON string 으로 변환
								planId:planId
							}),	//파라미터로 같이 담아서 보낼 것들
							success : (data)=>{
								if(data === 'true'){
									alert('신청 완료');
								}
								if(data === 'false'){
									alert('신청할 수 없습니다.');
								}
							},	//요청에 대해 성공한 경우 수행할 내용
							error :	()=>{
								alert('참가 실행 오류');
							}	//요청이 실패,오류난 경우 수행할 내용

						});
					});
					
					document.getElementById("deadBtn").addEventListener("click", function() {
						
						$.ajax({
							url : "/joinDead",	//Controller 요청 주소
							type: "POST",	//요청 method
							contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
							data : JSON.stringify({	//JSON string 으로 변환
								planId:planId
							}),	//파라미터로 같이 담아서 보낼 것들
							success : (data)=>{
								if(data === 'true'){
									alert('마감 완료');
									window.location.href = "/join_view";
								}
								if(data === 'false'){
									alert('마감 실패');
								}
							},	//요청에 대해 성공한 경우 수행할 내용
							error :	()=>{
								alert('마감 실행 오류');
							}	//요청이 실패,오류난 경우 수행할 내용
							
						});
						
					});
					
					document.getElementById("Btn").addEventListener("click", function() {
						console.log('버튼누름');
					});
					document.getElementById("deleteBtn").addEventListener("click", function() {
						console.log('삭제버튼누름');
						$.ajax({
							url : "/joinDelete",	//Controller 요청 주소
							type: "POST",	//요청 method
							contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
							data : JSON.stringify({	//JSON string 으로 변환
								planId:planId
							}),	//파라미터로 같이 담아서 보낼 것들
							success : (data)=>{
								if(data === 'true'){
									alert('삭제 완료');
									window.location.href = "/join_view";
								}
								if(data === 'false'){
									alert('삭제 실패');
								}
							},	//요청에 대해 성공한 경우 수행할 내용
							error :	()=>{
								alert('삭제 실행 오류');
							}	//요청이 실패,오류난 경우 수행할 내용
							
						});
						
					});
					
					</script>
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