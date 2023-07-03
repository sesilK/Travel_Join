package com.app.dao.review.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.review.ReviewDao;
import com.app.dto.CommentDto;
import com.app.dto.JoinDto;
import com.app.dto.MarkDto;
import com.app.dto.ReviewDto;
import com.app.dto.ReviewImgDto;

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
	public int selectReviewId(ReviewDto reviewDto) {
		
		ReviewDto resultDto = sqlsessionTemplate.selectOne("review_mapper.select_review_id", reviewDto);
		int reviewId = resultDto.getReviewId();
		
		return reviewId;
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
	public MarkDto selectMark(int reviewId, String userId, String sort) {

	    Map<String, Object> map = new HashMap<>();
	    map.put("reviewId", reviewId);
	    map.put("userId", userId);
	    map.put("sort", sort);
	    
		MarkDto markDto = sqlsessionTemplate.selectOne("review_mapper.select_review_mark", map);
	    
		return markDto;
	}
	
	@Override
	public int insertMark(int reviewId, String userId, String sort) {

		//추천/신고 테이블에 insert
		MarkDto markDto = new MarkDto(0, reviewId, userId, sort);
		sqlsessionTemplate.insert("review_mapper.insert_review_mark", markDto);
		//리뷰 테이블에 추천/신고 횟수 증가
		sqlsessionTemplate.update("review_mapper.update_review_mark_count", reviewId);
		//추천/신고 횟수 반환
		ReviewDto reviewDto
			= sqlsessionTemplate.selectOne("review_mapper.select_review_mark_count", markDto);
		int result = 0;
		if(sort.equals("L")) {
			result = reviewDto.getLikeCount();
		} else if (sort.equals("R")) {
			result = reviewDto.getReportCount();
		}
		return result;
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
	public int deleteReviewImg(int reviewId) {
		
		int result = sqlsessionTemplate.delete("review_mapper.delete_review_image", reviewId);
		
		return result;
	}

	@Override
	public int updateReviewDeleteAt(int reviewId) {
		
		int result = sqlsessionTemplate.update("review_mapper.update_review_delete_at", reviewId);
		
		return result;
	}

	@Override
	public int updateReview(ReviewDto reviewDto) {
		
		int result = sqlsessionTemplate.update("review_mapper.update_review_modify", reviewDto);
		
		return result;
	}

	@Override
	public int insertComment(CommentDto commentDto) {
		
		//댓글 테이블에 insert
		int result1 = sqlsessionTemplate.insert("review_mapper.insert_comment", commentDto);
		//리뷰 테이블에 댓글수 증가
		int reviewId = commentDto.getReviewId();
		int result2 = sqlsessionTemplate.update("review_mapper.update_review_comment_count", reviewId);
		
		return result1 + result2;
	}

	@Override
	public int updateCommentDeleteAt(int commentId, int reviewId) {
		
		//댓글 테이블에 삭제여부 update
		int result1 = sqlsessionTemplate.update("review_mapper.update_comment_delete_at", commentId);
		//리뷰 테이블에 댓글수 감소
		int result2 = sqlsessionTemplate.update("review_mapper.update_review_comment_count", reviewId);
		
		return result1 + result2;
	}

	@Override
	public int updateComment(CommentDto commentDto) {
		
		int result = sqlsessionTemplate.update("review_mapper.update_comment_modify", commentDto);
		
		return result;
	}

	@Override
	public List<CommentDto> selectCommentList(int reviewId) {
		
		List<CommentDto> list = 
				sqlsessionTemplate.selectList("review_mapper.select_comment_list", reviewId);
		
		return list;
	}

	@Override
	public List<JoinDto> selectJoinList(String userId) {

		List<JoinDto> list = 
				sqlsessionTemplate.selectList("review_mapper.select_review_join_list", userId);
		
		return list;
	}

	@Override
	public JoinDto selectJoinInfo(int planId) {
		
		JoinDto joinDto = sqlsessionTemplate.selectOne("review_mapper.select_review_join", planId);
		
		return joinDto;
	}
	
}
