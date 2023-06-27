package com.app.service.review.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.review.ReviewDao;
import com.app.dto.join.JoinDto;
import com.app.dto.review.CommentDto;
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
	public int returnReviewId(ReviewDto reviewDto) {
		
		int reviewId = reviewDao.selectReviewId(reviewDto);
		
		return reviewId;
	}
	
	@Override
	public int saveTemporaryReview(ReviewDto reviewDto) {
		
		int result = reviewDao.insertTemporaryReview(reviewDto);
		
		return result;
	}
	
	@Override
	public ReviewDto CheckIfTemporarySaved(String userId) {
		
		ReviewDto reviewDto = reviewDao.selectTemporaryReview(userId);
		
		return reviewDto;
	}
	
	@Override
	public int modifyTemporaryReview(ReviewDto reviewDto) {
		
		int result = reviewDao.updateTemporaryReview(reviewDto);
		
		return result;
	}
	
	@Override
	public int removeTemporaryReview(String userId) {
		
		int result = reviewDao.deleteTemporaryReview(userId);
		
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
	public int removeReviewImage(int reviewId) {

		int result = reviewDao.deleteReviewImg(reviewId);
		
		return result;

	}

	@Override
	public int blindReview(int reviewId) {

		int result = reviewDao.updateReviewDeleteAt(reviewId);
		
		return result;
	}

	@Override
	public int modifyReview(ReviewDto reviewDto) {

		int result = reviewDao.updateReview(reviewDto);
		
		return result;
	}

	@Override
	public int createComment(CommentDto CommentDto) {
		
		int result = reviewDao.insertComment(CommentDto);
		
		return result;
	}

	@Override
	public int blindComment(int CommentId, int reviewId) {
		
		int result = reviewDao.updateCommentDeleteAt(CommentId, reviewId);
		
		return result;
	}

	@Override
	public int modifyComment(CommentDto CommentDto) {
		
		int result = reviewDao.updateComment(CommentDto);
		
		return result;
	}

	@Override
	public List<CommentDto> findCommentList(int reviewId) {
		
		List<CommentDto> list = reviewDao.selectCommentList(reviewId);
		
		return list;
	}

	@Override
	public List<JoinDto> findJoinList(String userId) {

		List<JoinDto> list = reviewDao.selectJoinList(userId);
		
		return list;
	}

	@Override
	public JoinDto findJoinInfo(int planId) {
		
		JoinDto joinDto = reviewDao.selectJoinInfo(planId);
		
		return joinDto;
	}

}
