package com.app.dto.join;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JoinDto {

	String no;		//게시글번호
	String id;		// 작성자 아이디
	String title;	//글 제목
	String content; //게시글 내용
	String regDate; // 작성일 

//	String destination; //여행지 -> 해시태그로 대체 (카테고리 역황)
//	String content_img;	//게시글 사진
//	String start_date; //여행 시작일
//	String end_date; //여행 종료일
//	int participants; //참가인원
//	int view_count; //조회수
	
//	String hashtags;
	
}
