package com.app.dao.review.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.review.ReviewDao;
import com.app.dto.review.ReviewDto;

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
	public ReviewDto selectReview(String userId) {
		
		ReviewDto reviewDto = 
				sqlsessionTemplate.selectOne("review_mapper.select_reviewId", userId);
		
		return reviewDto;
	}
	
	@Override
	public int updateViews(int reviewId) {
		
		int result = sqlsessionTemplate.update("update_review_views_increase", reviewId);
		
		return result;
	}
	
	
	
}
