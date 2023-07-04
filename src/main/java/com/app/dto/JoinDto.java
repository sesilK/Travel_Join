package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
	String planState;			// 글 상태 (모집중0,모집마감1,삭제함2)

	int views; 	// 조회수
	int likes; 	// 좋아요
	
	int imageNo;
	String fileName;
	String firstImage;
	List<String> imageFileNameList; //이미파일명 리스트 ??

	String nick;
	String birth;
	String gender;
	String imageId;
}
