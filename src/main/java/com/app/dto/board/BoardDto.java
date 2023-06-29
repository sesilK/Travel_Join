package com.app.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
	
	int planId;				// 게시글 번호 (조회용)
	String userId;			// 아이디
	String title;			// 글 제목
	String content;			// 글 내용
	String destination;		// 도착지
	int personnel;			// 모집인원
	
	String startDay; 		// 여행 시작일
	String endDay; 			// 여행 종료일
	String regDate; 		// 게시글 작성일
	String finishDate; 		// 게시글 마감일 
	String planState;		// 여행계획여부
	

	
	
}
