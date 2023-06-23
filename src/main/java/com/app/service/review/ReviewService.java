package com.app.service.review;

import java.util.List;
import java.util.Map;

import com.app.dto.review.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.dto.review.ReviewImgDto;

public interface ReviewService {

	public int createReview(ReviewDto reviewDto); //글 작성
	
	public ReviewDto returnReview(ReviewDto reviewDto); //작성글 반환
	
	public int saveTemporaryReview(ReviewDto reviewDto); //글 임시저장
	
	public ReviewDto CheckIfTemporarySaved(String userId); //임시저장 조회
	
	public int modifyTemporaryReview(ReviewDto reviewDto); //임시저장 변경
	
	public int removeTemporaryReview(String userId); //임시저장 삭제
	
	public List<ReviewDto> findReviewList(Map<String, String> map); //글목록 불러오기
	
	public ReviewDto findReview(int reviewId); //글상세 불러오기
	
	public int increaseViews(ReviewDto reviewDto);	//조회수 증가
	
	public LikeDto CheckIfRecommended(int reviewId, String userId); //추천여부 확인
	
	public int reviewRecommend(int reviewId, String userId);	//추천하기

	public int uploadReviewImage(ReviewImgDto reviewImgDto); //이미지파일명 저장
	
	public ReviewImgDto findReviewImage(String fileName); //이미지파일명 조회
	
	public int removeReviewImage(String fileName); //이미지파일명 삭제
}
