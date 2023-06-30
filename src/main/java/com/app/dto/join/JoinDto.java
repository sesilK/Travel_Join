package com.app.dto.join;

import java.util.List; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JoinDto {

	int planId;				// 게시글 번호 (조회용)
	String userId;			// 아이디
	String title;			// 글 제목
	String content;			// 글 내용
	String destination;		// 상세목적지 
	int planType;			// 목적지타입 ex)국내0, 해외1
	int personnel;			// 모집인원
	
	String startDay; 		// 여행 시작일
	String endDay; 			// 여행 종료일
	String regDate; 		// 모집 시작일
	String finishDate; 		// 모집 마감일 
	int planState;		// 글 상태
	
	
	int views; 	// 조회수
	int likes; 	// 좋아요
	
	int imageNo;
	String fileName;
	String firstImage;
	List<String> imageFileNameList; //이미파일명 리스트 ??
	

//	String destination; //여행지 -> 해시태그로 대체 (카테고리 역황)
//	String content_img;	//게시글 사진
//	String start_date; //여행 시작일
//	String end_date; //여행 종료일
//	int participants; //참가인원
//	int view_count; //조회수
	
//	String hashtags;
	
}
