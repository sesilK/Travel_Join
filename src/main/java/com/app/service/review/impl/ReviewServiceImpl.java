package com.app.service.review.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.review.ReviewDao;
import com.app.dto.review.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.dto.review.ReviewImgDto;
import com.app.service.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDao reviewDao;
	
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
	public ReviewDto findReview(String userId){

		ReviewDto item = reviewDao.selectReview(userId);
		
		return item;
	}
	
	@Override
	public int reviewRecommend(int reviewId, String userId) {
		
		int result = reviewDao.insertLike(reviewId, userId);
		
		return result;
	}

	@Override
	public LikeDto CheckIfRecommended(int reviewId, String userId) {

		LikeDto likeDto = reviewDao.selectLike(reviewId, userId);
		
		return likeDto;
	}

	@Override
	public int increaseViews(ReviewDto reviewDto) {

		int result = reviewDao.updateViews(reviewDto);
		
		return result;
	}

	@Override
	public int uploadReviewImage(ReviewImgDto reviewImgDto) {

		int result = reviewDao.insertReviewImg(reviewImgDto);
		
		return result;
	}

	@Override
	public ReviewImgDto findReviewImage(String imageId) {

		ReviewImgDto img = reviewDao.selectReviewImg(imageId);
		
		return img;
	}

	@Override
	public int removeReviewImage(String fileName) {

		int result = reviewDao.deleteReviewImg(fileName);
		
		return result;

	}

}
