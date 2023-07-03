package com.app.service.board.impl;

import java.util.List;

import com.app.dto.JoinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.board.BoardDao;
import com.app.dto.BoardDto;
import com.app.dto.PartyDto;
import com.app.service.board.BoardService;

@Service
public class BoardServiceImpl implements BoardService{


	@Autowired
	BoardDao boardDao;

	@Override
	public JoinDto findPostById(int planId) {
		// TODO Auto-generated method stub
		JoinDto item = boardDao.findPostById(planId);
		return item;
	}

	@Override
	public List<BoardDto> myTeamDetail(String userId) {
		// TODO Auto-generated method stub
		List<BoardDto> partyMembers = boardDao.myTeamDetail(userId);
		return partyMembers;
	}


	@Override
	public void joinParty(PartyDto partyDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int joinBoardViewIncrease(int planId) {

		int result = boardDao.joinBoardViewIncrease(planId);

		return result;
	}

	
	

}
