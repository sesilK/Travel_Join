package com.app.dao.review.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.review.ReviewDao;
import com.app.dto.review.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.dto.review.ReviewImgDto;

@Repository
public class ReviewDaoImpl implements ReviewDao{

	@Autowired
	SqlSessionTemplate sqlsessionTemplate;
	
	@Override
	public int insertReview(ReviewDto reviewDto) {

		int result = sqlsessionTemplate.insert("review_mapper.insert_review", reviewDto);
		
		return result;
	}
	
	@Override
	public ReviewDto selectReviewId(ReviewDto reviewDto) {
		
		ReviewDto resultDto = sqlsessionTemplate.selectOne("review_mapper.select_review_id", reviewDto);
		
		return resultDto;
	}
	
	@Override
	public int insertTemporaryReview(ReviewDto reviewDto) {
		
		int result = sqlsessionTemplate.insert("review_mapper.insert_review_temp", reviewDto);
		
		return result;
	}
	
	@Override
	public ReviewDto selectTemporaryReview(String userId) {
		
		ReviewDto reviewDto = sqlsessionTemplate.selectOne("review_mapper.select_review_temp", userId);
		
		return reviewDto;
	}
	
	@Override
	public int updateTemporaryReview(ReviewDto reviewDto) {
		
		int result = sqlsessionTemplate.update("review_mapper.update_review_temp", reviewDto);
		
		return result;
	}
	
	@Override
	public int deleteTemporaryReview(String userId) {
		
		int result = sqlsessionTemplate.delete("review_mapper.delete_review_temp", userId);
		
		return result;
	}

	@Override
	public List<ReviewDto> selectReviewList(Map<String, String> map) {
		List<ReviewDto> list = 
				sqlsessionTemplate.selectList("review_mapper.select_review_list", map);
		
		return list;
	}
	
	@Override
	public ReviewDto selectReview(int reviewId) {
		
		ReviewDto reviewDto = 
				sqlsessionTemplate.selectOne("review_mapper.select_review_view", reviewId);
		
		return reviewDto;
	}
	
	@Override
	public int updateViews(ReviewDto reviewDto) {
		
		int result = sqlsessionTemplate.update("update_review_views_increase", reviewDto);
		
		return result;
	}
	
	@Override
	public LikeDto selectLike(int reviewId, String userId) {

	    Map<String, Object> map = new HashMap<>();
	    map.put("reviewId", reviewId);
	    map.put("userId", userId);
	    
		LikeDto likeDto = sqlsessionTemplate.selectOne("review_mapper.select_review_like", map);
	    
		return likeDto;
	}
	
	@Override
	public int insertLike(int reviewId, String userId) {

		//추천 테이블에 insert
		LikeDto newLikeDto = new LikeDto(reviewId, userId);
		int result1 = sqlsessionTemplate.insert("review_mapper.insert_review_like", newLikeDto);
		//리뷰 테이블에 추천수 증가
		int result2 = sqlsessionTemplate.update("review_mapper.update_review_like_increase", reviewId);
		
		return result1 + result2;

	}
	
	@Override
	public int insertReviewImg(ReviewImgDto reviewImgDto) {

		int result = sqlsessionTemplate.insert("review_mapper.insert_review_image", reviewImgDto);
		
		return result;
	}

	@Override
	public ReviewImgDto selectReviewImg(String fileName) {

		ReviewImgDto reviewImgDto = 
				sqlsessionTemplate.selectOne("review_mapper.select_review_image", fileName);
		
		return reviewImgDto;
	}

	@Override
	public int deleteReviewImg(String fileName) {
		
		int result = sqlsessionTemplate.delete("review_mapper.delete_review_image", fileName);
		
		return result;
	}
	
}
