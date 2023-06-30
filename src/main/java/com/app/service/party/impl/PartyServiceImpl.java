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
    public PartyDto joinParty(BoardDto boardDto) {

        int planId = boardDto.getPlanId();
        String userId = boardDto.getUserId();
		return null;       
       
    }

//    @Override
//    public List<PartyDto> myTeamDetail(int planId) {
//    	// TODO Auto-generated method stub
//    	return null;
//    }

	@Override
	public PartyDto addMember(int no) {
		// TODO Auto-generated method stub
		return null;
	}


	


	

	
}
