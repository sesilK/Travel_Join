package com.app.service.board.impl;

import com.app.dao.board.BoardDao;
import com.app.dto.BoardDto;
import com.app.dto.JoinDto;
import com.app.dto.PartyDto;
import com.app.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public List<JoinDto> select_images_by_planId(int planId) {
		List<JoinDto> list = boardDao.select_images_by_planId(planId);
		return list;
	}


	@Override
	public void joinParty(PartyDto partyDto) {
		// TODO Auto-generated method stub
		
	}

	
	

}
