package com.app.service.review;

import java.util.List;
import java.util.Map;

import com.app.dto.join.JoinDto;
import com.app.dto.review.CommentDto;
import com.app.dto.review.MarkDto;
import com.app.dto.review.ReviewDto;
import com.app.dto.review.ReviewImgDto;

public interface ReviewService {

	public int createReview(ReviewDto reviewDto); //글 작성
	
	public int returnReviewId(ReviewDto reviewDto); //작성글 번호찾기
	
	public int saveTemporaryReview(ReviewDto reviewDto); //글 임시저장
	
	public ReviewDto CheckIfTemporarySaved(String userId); //임시저장 조회
	
	public int modifyTemporaryReview(ReviewDto reviewDto); //임시저장 변경
	
	public int removeTemporaryReview(String userId); //임시저장 삭제
	
	public List<ReviewDto> findReviewList(Map<String, String> map); //글목록 불러오기
	
	public ReviewDto findReview(int reviewId); //글상세 불러오기
	
	public int increaseViews(ReviewDto reviewDto);	//조회수 증가
	
	public MarkDto CheckReviewMark(int reviewId, String userId, String sort); //추천/신고 여부 확인
	
	public int reviewMark(int reviewId, String userId, String sort); //추천/신고하기 (횟수 반환)

	public int uploadReviewImage(ReviewImgDto reviewImgDto); //이미지파일명 저장
	
	public ReviewImgDto findReviewImage(String fileName); //이미지파일명 조회
	
	public int removeReviewImage(int reviewId); //이미지파일명 전부 삭제
	
	public int blindReview(int reviewId); //글 삭제
	
	public int modifyReview(ReviewDto reviewDto); //글 수정
	
	public int createComment(CommentDto CommentDto); //댓글 작성 (댓글수 증가)
	
	public int blindComment(int CommentId, int reviewId); //댓글 삭제 (댓글수 감소)
	
	public int modifyComment(CommentDto CommentDto); //댓글 수정
	
	public List<CommentDto> findCommentList(int reviewId); //댓글목록 불러오기
	
	public List<JoinDto> findJoinList(String userId); //여행목록 불러오기
	
	public JoinDto findJoinInfo(int planId); //여행정보 찾기
}
