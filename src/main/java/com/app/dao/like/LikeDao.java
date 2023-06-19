package com.app.dao.like;

import com.app.dto.like.LikeDto;

public interface LikeDao {

	public LikeDto selectLike(int reviewId, String userId);	//추천여부 확인
	
	public int insertLike(int reviewId, String userId); //추천하기
	
}
