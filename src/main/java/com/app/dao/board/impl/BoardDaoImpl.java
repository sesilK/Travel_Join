package com.app.dao.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.board.BoardDao;
import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;

@Repository
public class BoardDaoImpl implements BoardDao{
	

	@Autowired
	SqlSessionTemplate sqlsessionTemplate;
	
	
	@Override
	public BoardDto findPostById(int planId) {
		
		BoardDto boardDto = 
				sqlsessionTemplate.selectOne("board_mapper.select_findPostById", planId);
		
		return boardDto;
	}

	@Override
	public void plusView(int planId) {
		// TODO Auto-generated method stub
		sqlsessionTemplate.update("board_mapper.",planId);
	}

	@Override
	public List<PartyDto> getPartyMembersByPlanId(int planId) {
		
		return sqlsessionTemplate.selectList("party_mapper.myTeamDetail", planId);
	}



		
}
