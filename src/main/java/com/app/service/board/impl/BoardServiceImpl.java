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
	public void plusView(int planId) {
		// TODO Auto-generated method stub
		boardDao.plusView(planId);
	}

	@Override
	public List<PartyDto> getPartyMembersByPlanId(int planId) {
		// TODO Auto-generated method stub
		List<PartyDto> member = boardDao.getPartyMembersByPlanId(planId);
		return member;
	}

	
}
