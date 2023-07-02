package com.app.service.party.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.party.PartyDao;
import com.app.dto.BoardDto;
import com.app.dto.PartyDto;
import com.app.service.party.PartyService;

@Service
public class PartyServiceImpl implements PartyService{

	@Autowired
	PartyDao partyDao;


	/*
	 * @Override public PartyDto joinParty(String userId) { // TODO Auto-generated
	 * method stub PartyDto item = partyDao.joinParty(userId);
	 * 
	 * return item; }
	 */


	@Override
	public PartyDto addMember(int no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PartyDto joinParty(BoardDto boardDto) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PartyDto myTeamDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
