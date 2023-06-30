package com.app.service.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.board.BoardDao;
import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;
import com.app.service.board.BoardService;

@Service
public class BoardServiceImpl implements BoardService{


	@Autowired
	BoardDao boardDao;

	@Override
	public BoardDto findPostById(int planId) {
		// TODO Auto-generated method stub
		BoardDto item = boardDao.findPostById(planId);
		return item;
	}

	@Override
<<<<<<< HEAD
	public List<BoardDto> myTeamDetail(String userId) {
=======
	public void plusView(int planId) {
		// TODO Auto-generated method stub
		boardDao.plusView(planId);
	}

	@Override
	public List<PartyDto> getPartyMembersByPlanId(int planId) {
>>>>>>> dcb72b8b0c0a421d62435665e8382dc0b8dd380b
		// TODO Auto-generated method stub
		return null;
	}

<<<<<<< HEAD
	@Override
	public void joinParty(PartyDto partyDto) {
		// TODO Auto-generated method stub
		
	}

	
	
//	@Override
//	public List<BoardDto> myTeamDetail(String userId) {
//		// TODO Auto-generated method stub
//		List<BoardDto> partyMembers = boardDao.myTeamDetail(userId);
//		return partyMembers;
//	}
=======
>>>>>>> dcb72b8b0c0a421d62435665e8382dc0b8dd380b
	
}
