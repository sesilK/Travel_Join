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

<<<<<<< HEAD
	

=======
	@Override
	public void plusView(int planId) {
		// TODO Auto-generated method stub
		sqlsessionTemplate.update("board_mapper.",planId);
	}
>>>>>>> dcb72b8b0c0a421d62435665e8382dc0b8dd380b

	@Override
	public List<BoardDto> myTeamDetail(String userId) {		
		List<BoardDto> boardDto = 
				sqlsessionTemplate.selectList("board_mapper.myTeamDetail", userId);
		return boardDto;
	}



<<<<<<< HEAD

	@Override
	public void joinParty(PartyDto partyDto) {
		// TODO Auto-generated method stub
		
	}


=======
>>>>>>> dcb72b8b0c0a421d62435665e8382dc0b8dd380b
		
}
