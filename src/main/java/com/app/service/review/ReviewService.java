package com.app.service.review;

import java.util.List;
import java.util.Map;

import com.app.dto.like.LikeDto;
import com.app.dto.review.ReviewDto;

public interface ReviewService {

	public int createReview(ReviewDto reviewDto); //글 작성
	
	public List<ReviewDto> findReviewList(Map<String, String> map); //글목록 불러오기
	
	public ReviewDto findReview(int reviewId); //글상세 불러오기
	
	public int increaseViews(int reviewId);	//조회수 증가
	
	public LikeDto CheckIfRecommended(int reviewId, String userId); //추천여부 확인
	
	public int reviewRecommend(int reviewId, String userId);	//추천하기
}
