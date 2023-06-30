package com.app.dao.party.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.party.PartyDao;
import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;

@Repository
public class PartyDaoImpl implements PartyDao{
	
		@Autowired
		SqlSessionTemplate sqlsessionTemplate;
		
		
		
		@Override
		public PartyDto joinParty(BoardDto boardDto) {
		    sqlsessionTemplate.insert("board_mapper.joinParty");
		    
		    // 파티 정보를 다시 조회하여 PartyDto 객체 생성
		    PartyDto partyDto = sqlsessionTemplate.selectOne("board_mapper.getParty");
		    
		    return partyDto;
		}
		
//		@Override
//		public List<PartyDto> myTeamDetail(int planId) {
//			sqlsessionTemplate.selectOne("party_mapper.myTeamDetail");
//			
//			List<PartyDto> partyDto = sqlsessionTemplate.selectList("party_mapper.getPartyMembersByPlanId");
//			    
//		    return partyDto;
//		}
		

		@Override
		public PartyDto addMember(int planId) {
			// TODO Auto-generated method stub
			return null;
		}


			
	}

