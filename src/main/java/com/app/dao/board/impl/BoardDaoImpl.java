package com.app.dao.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.board.BoardDao;
import com.app.dto.JoinDto;
import com.app.dto.MarkDto;
import com.app.dto.PartyDto;
import com.app.dto.ReviewDto;

@Repository
public class BoardDaoImpl implements BoardDao{
	

	@Autowired
	SqlSessionTemplate sqlsessionTemplate;
	
	
	@Override
	public JoinDto findPostById(int planId) {

		JoinDto joinDto =
				sqlsessionTemplate.selectOne("board_mapper.select_findPostById", planId);
		
		return joinDto;
	}

	@Override
	public List<JoinDto> myTeamDetail(String userId) {		
		List<JoinDto> joinDto = 
				sqlsessionTemplate.selectList("board_mapper.myTeamDetail", userId);
		return joinDto;
	}


	@Override
	public void joinParty(PartyDto partyDto) {
		// TODO Auto-generated method stub
		
	}
	
	public int joinBoardViewIncrease(int planId) {

		int result = sqlsessionTemplate.update("update_joinBoardViewIncrease", planId);

		return result;
	}

	@Override
	public MarkDto selectBoardMark(int planId, String userId, String sort) {
		
	    Map<String, Object> map = new HashMap<>();
	    map.put("planId", planId);
	    map.put("userId", userId);
	    map.put("sort", sort);
	    
		MarkDto markDto = sqlsessionTemplate.selectOne("board_mapper.select_join_mark", map);
	    
		return markDto;
	}

	@Override
	public int insertBoardMark(int planId, String userId, String sort) {
		
		//추천 테이블에 insert
		MarkDto markDto = new MarkDto(planId, 0, userId, sort);
		sqlsessionTemplate.insert("board_mapper.insert_join_mark", markDto);
		//모집 테이블에 추천 횟수 증가
		sqlsessionTemplate.update("board_mapper.update_join_mark_count", planId);
		//추천 횟수 반환
		JoinDto joinDto
			= sqlsessionTemplate.selectOne("board_mapper.select_join_mark_count", markDto);
		int result = 0;
		if(sort.equals("L")) {
			result = joinDto.getLikes();
		} else {
			
		}
		return result;
	}
	
}
