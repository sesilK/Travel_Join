package com.app.dao.party.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.party.PartyDao;
import com.app.dto.PartyDto;

@Repository
public class PartyDaoImpl implements PartyDao{
	
		@Autowired
		SqlSessionTemplate sqlsessionTemplate;
		
		@Override
		public PartyDto joinParty(String userId) {
		    sqlsessionTemplate.update("board_mapper.joinParty", userId);
		    
		    // 파티 정보를 다시 조회하여 PartyDto 객체 생성
		    PartyDto partyDto = sqlsessionTemplate.selectOne("board_mapper.getParty", userId);
		    
		    return partyDto;
		}

		
		@Override
		public PartyDto myTeamDetail() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PartyDto addMember(int no) {
			// TODO Auto-generated method stub
			return null;
		}
			
	}

