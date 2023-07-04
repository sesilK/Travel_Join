package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Data
public class ReviewDto {
	
	int reviewId;		//글번호
	int planId;			//여행
	String userId;		//작성자
	String title;		//제목
	String content;		//내용
	float stars;		//별점
	int views;			//조회
	int likeCount;		//추천
	int commentCount;	//댓글수
	int reportCount;	//신고횟수
	String createDate;	//작성일
	String updateDate;	//수정일
	String deleteAt;	//삭제여부
	String deleteDate;	//삭제일
	
	// DB에는 없는 항목
	String planInfo;	//여행정보
	int planType;		//여행분류(국내0/해외1)
	String destiantion;		//여행지
	String nick;		//작성자닉네임
	List<String> imageFileNameList; //파일명 리스트
	
}