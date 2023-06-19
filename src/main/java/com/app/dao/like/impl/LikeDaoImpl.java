package com.app.dao.like.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.like.LikeDao;
import com.app.dto.like.LikeDto;

@Repository
public class LikeDaoImpl implements LikeDao {

	@Autowired
	SqlSessionTemplate sqlsessionTemplate;

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

}
