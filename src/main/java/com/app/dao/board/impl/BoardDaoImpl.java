package com.app.dao.board.impl;

import com.app.dao.board.BoardDao;
import com.app.dto.BoardDto;
import com.app.dto.JoinDto;
import com.app.dto.PartyDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	public List<BoardDto> myTeamDetail(String userId) {		
		List<BoardDto> boardDto = 
				sqlsessionTemplate.selectList("board_mapper.myTeamDetail", userId);
		return boardDto;
	}

	@Override
	public List<JoinDto> select_images_by_planId(int planId) {
		List<JoinDto> list = sqlsessionTemplate.selectList("board_mapper.select_images_by_planId", planId);
		return list;
	}


	@Override
	public void joinParty(PartyDto partyDto) {
		// TODO Auto-generated method stub
		
	}
}
