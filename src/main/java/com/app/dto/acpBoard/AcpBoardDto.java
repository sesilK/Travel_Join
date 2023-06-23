package com.app.dto.acpBoard;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AcpBoardDto {
	String id;		// 작성자 아이디
	String isClosed;
	String title;	//글 제목
	String content_text; //게시글 내용
	String content_img;	//게시글 사진
	String hashtags;
//	String destination; //여행지 -> 해시태그로 대체
	String start_date; //여행 시작일
	String end_date; //여행 종료일
	int participants; //참가인원
	Date reg_date; // 작성일 
	int view_count; //조회수
	
	

}
