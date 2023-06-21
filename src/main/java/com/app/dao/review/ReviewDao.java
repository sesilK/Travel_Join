package com.app.dao.review;

import java.util.List;
import java.util.Map;

import com.app.dto.review.ReviewDto;

public interface ReviewDao {

	public int insertReview(ReviewDto reviewDto); //글 작성
	
	public List<ReviewDto> selectReviewList(Map<String, String> map); //글목록 불러오기
	
	public ReviewDto selectReview(int reviewId); //글상세 불러오기
	
	public ReviewDto selectReview(String userId); //글번호 찾기
	
	public int updateViews(int reviewId); //조회수 증가
}
