package com.app.dao.review;

import java.util.List;
import java.util.Map;

import com.app.dto.review.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.dto.review.ReviewImgDto;

public interface ReviewDao {

	public int insertReview(ReviewDto reviewDto); //글 작성
	
	public List<ReviewDto> selectReviewList(Map<String, String> map); //글목록 불러오기
	
	public ReviewDto selectReview(int reviewId); //글상세 불러오기
	
	public ReviewDto selectReview(String userId); //글번호 찾기
	
	public int updateViews(ReviewDto reviewDto); //조회수 증가

	public LikeDto selectLike(int reviewId, String userId);	//추천여부 확인
	
	public int insertLike(int reviewId, String userId); //추천하기
	
	public int insertReviewImg(ReviewImgDto reviewImgDto); //이미지파일명 저장
	
	public ReviewImgDto selectReviewImg(String fileName); //이미지파일명 조회
	
	public int deleteReviewImg(String fileName); //이미지파일명 삭제
	
	
}
