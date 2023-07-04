package com.app.service.board.impl;

import java.util.List;

import com.app.dto.JoinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.board.BoardDao;
import com.app.dto.JoinDto;
import com.app.dto.MarkDto;
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
	public List<JoinDto> myTeamDetail(String userId) {
		// TODO Auto-generated method stub
		List<JoinDto> partyMembers = boardDao.myTeamDetail(userId);
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

	@Override
	public List<JoinDto> travelTogether(String userId) {
		// TODO Auto-generated method stub
		List<JoinDto> travelTogether = boardDao.travelTogether(userId);
		return travelTogether;
	}

	@Override
	public List<JoinDto> myPartying(String userId) {
		// TODO Auto-generated method stub
		
		List<JoinDto> myPartying = boardDao.myPartying(userId);
		return myPartying;
	public int joinBoardViewIncrease(int planId) {

		int result = boardDao.joinBoardViewIncrease(planId);

		return result;
	}

	@Override
	public MarkDto selectBoardMark(int planId, String userId, String sort) {
		
		MarkDto markDto = boardDao.selectBoardMark(planId, userId, sort);
		
		return markDto;
	}

	@Override
	public int insertBoardMark(int planId, String userId, String sort) {
		
		int result = boardDao.insertBoardMark(planId, userId, sort);
		
		return result;
	}

	
	

}
