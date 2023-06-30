package com.app.service.party.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.party.PartyDao;
import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;
import com.app.service.party.PartyService;

@Service
public class PartyServiceImpl implements PartyService{

	@Autowired
	PartyDao partyDao;

	@Override
	public int joinParty(PartyDto partyDto) {
		// TODO Auto-generated method stub
		int reslut = partyDao.joinParty(partyDto);
		return reslut;
	}

	@Override
	public List<PartyDto> myTeamDetail(int no) {
		// TODO Auto-generated method stub
		List<PartyDto> list = partyDao.myTeamDetail(no);
		return list;
	}

	@Override
	public int addMember(int no) {
		// TODO Auto-generated method stub
		int reslut = partyDao.addMember(no);
		return reslut;
	}

	@Override
	public int checkStatus(PartyDto partyDto) {
		int reslut = partyDao.checkStatus(partyDto);
		return reslut;
	}

	@Override
	public int joinDead(PartyDto partyDto) {
		int reslut = partyDao.joinDead(partyDto);
		return reslut;
	}

	@Override
	public int joinDelete(PartyDto partyDto) {
		int reslut = partyDao.joinDelete(partyDto);
		return reslut;
	}



	/*
	 * @Override public PartyDto joinParty(String userId) { // TODO Auto-generated
	 * method stub PartyDto item = partyDao.joinParty(userId);
	 * 
	 * return item; }
	 */


	

	

	
}
