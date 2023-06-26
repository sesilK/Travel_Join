package com.app.dao.review;

import java.util.List;
import java.util.Map;

import com.app.dto.review.CommentDto;
import com.app.dto.review.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.dto.review.ReviewImgDto;

public interface ReviewDao {

	public int insertReview(ReviewDto reviewDto); //글 작성
	
	public int selectReviewId(ReviewDto reviewDto); //작성글 번호찾기
	
	public int insertTemporaryReview(ReviewDto reviewDto); //글 임시저장
	
	public ReviewDto selectTemporaryReview(String userId); //임시저장 조회
	
	public int updateTemporaryReview(ReviewDto reviewDto); //임시저장 변경
	
	public int deleteTemporaryReview(String userId); //임시저장 삭제
	
	public List<ReviewDto> selectReviewList(Map<String, String> map); //글목록 불러오기
	
	public ReviewDto selectReview(int reviewId); //글상세 불러오기
	
	public int updateViews(ReviewDto reviewDto); //조회수 증가

	public LikeDto selectLike(int reviewId, String userId);	//추천여부 확인
	
	public int insertLike(int reviewId, String userId); //추천하기 (증가한 추천수 반환)
	
	public int insertReviewImg(ReviewImgDto reviewImgDto); //이미지파일명 저장
	
	public ReviewImgDto selectReviewImg(String fileName); //이미지파일명 조회
	
	public int deleteReviewImg(int reviewId); //이미지파일명 전부 삭제
	
	public int updateReviewDeleteAt(int reviewId); //글 삭제
	
	public int updateReview(ReviewDto reviewDto); //글 수정
	
	public int insertComment(CommentDto commentDto); //댓글 작성 (댓글수 증가)
	
	public int updateCommentDeleteAt(int commentId, int reviewId); //댓글 삭제 (댓글수 감소)
	
	public int updateComment(CommentDto commentDto); //댓글 수정
	
	public int selectCommentCount(int reviewId); //댓글수 불러오기
	
	public List<CommentDto> selectCommentList(int reviewId); //댓글목록 불러오기
}
