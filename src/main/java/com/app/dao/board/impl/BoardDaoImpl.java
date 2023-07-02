package com.app.dao.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.board.BoardDao;
import com.app.dto.BoardDto;
import com.app.dto.PartyDto;

@Repository
public class BoardDaoImpl implements BoardDao{
	

	@Autowired
	SqlSessionTemplate sqlsessionTemplate;
	
	
	@Override
	public BoardDto findPostById(String userId) {
		
		BoardDto boardDto = 
				sqlsessionTemplate.selectOne("board_mapper.select_findPostById", userId);
		
		return boardDto;
	}


	@Override
	public List<PartyDto> getPartyMembersByPlanId(int planId) {
		
		return sqlsessionTemplate.selectList("party_mapper.myTeamDetail", planId);
	}

		
}
