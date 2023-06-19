package com.app.service.review.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.like.LikeDao;
import com.app.dao.review.ReviewDao;
import com.app.dto.like.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.service.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	LikeDao likeDao;
	
	@Override
	public int createReview(ReviewDto reviewDto) {
		
		int result = reviewDao.insertReview(reviewDto);
		
		return result;
	}

	@Override
	public List<ReviewDto> findReviewList(Map<String, String> map) {

		List<ReviewDto> list = reviewDao.selectReviewList(map);
		
		return list;
	}

	@Override
	public ReviewDto findReview(int reviewId){

		ReviewDto item = reviewDao.selectReview(reviewId);
		
		return item;
	}

	@Override
	public int reviewRecommend(int reviewId, String userId) {
		
		int result = likeDao.insertLike(reviewId, userId);
		
		return result;
	}

	@Override
	public LikeDto CheckIfRecommended(int reviewId, String userId) {

		LikeDto likeDto = likeDao.selectLike(reviewId, userId);
		
		return likeDto;
	}

	@Override
	public int increaseViews(int reviewId) {

		int result = reviewDao.updateViews(reviewId);
		
		return result;
	}

	
}
