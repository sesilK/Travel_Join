<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="../../../resources/css/acpBoard.css" rel="stylesheet" type="text/css">
<html xmlns:th="http://www.thymeleaf.org">
<title>Insert title here</title>
</head>
<body>
	<h1>글 작성 페이지</h1>

<h2 th:text="${acpBoard.title}">제목입니다</h2>
<p th:text="${acpBoard.content}">내용이들어갈부분입니다</p>


	<div class="range-slider">
		<input class="range-slider__range" type="range" value="0" min="0" max="5"> <span class="range-slider__value">0</span> <label>명</label>
	</div>

	<div class="container">
		<input type="text" id="startDate" placeholder="날짜 선택" readonly="readonly">
	</div>

	<div class="container">
		<input type="text" id="endDate" placeholder="날짜 선택" readonly="readonly">
	</div>


	<div>
		<!-- 배경사진추가 -->
		<!-- 간단한 설명 -->
		<!-- 해시태그  -->
	</div>
	
	<button type="reset">취소</button>
	<button type="submit">작성완료</button>

	<script type="text/javascript">
    $(document).ready(function () {
            $.datepicker.setDefaults($.datepicker.regional['ko']); 
            $( "#startDate" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yymmdd",
                 minDate: 0,                       
                 onClose: function( selectedDate ) {    
                      //시작일(startDate) datepicker가 닫힐때
                      //종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                     $("#endDate").datepicker( "option", "minDate", selectedDate );
                 }    
 
            });
            $( "#endDate" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yymmdd",
                 maxDate: 20,                       // 선택할수있는 최대날짜, ( 0 : 오늘 이후 날짜 선택 불가)
               // minDate: 0,
                minDate : new Date(startDate),
                onClose: function( selectedDate ) {    
                     // 종료일(endDate) datepicker가 닫힐때
                     // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
                     $("#startDate").datepicker( "option", "maxDate", selectedDate );
                 }    
 
            });    
    });
    
    
    
    var rangeSlider = function(){
    	  var slider = $('.range-slider'),
    	      range = $('.range-slider__range'),
    	      value = $('.range-slider__value');
    	    
    	  slider.each(function(){

    	    value.each(function(){
    	      var value = $(this).prev().attr('value');
    	      $(this).html(value);
    	    });

    	    range.on('input', function(){
    	      $(this).next(value).html(this.value);
    	    });
    	  });
    	};

    	rangeSlider();
    
    
    	
    	
    
</script>

</body>
</html>