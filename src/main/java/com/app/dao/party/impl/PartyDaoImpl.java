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
		public int joinParty(PartyDto partyDto) {

		    // 파티 정보를 다시 조회하여 PartyDto 객체 생성
		    int result = sqlsessionTemplate.insert("party_mapper.joinParty", partyDto);
		    
		    return result;
		}

		@Override
		public List<PartyDto> myTeamDetail(int no) {
			// TODO Auto-generated method stub
			List<PartyDto> list = sqlsessionTemplate.selectList("party_mapper.myTeamDetail",no);
			return list;
		}

		@Override
		public int addMember(int no) {
			// TODO Auto-generated method stub
			int result = sqlsessionTemplate.update("party_mapper.addMember", no);
			return result;
		}

		@Override
		public int checkStatus(PartyDto partyDto) {
			int result = sqlsessionTemplate.selectOne("party_mapper.checkStatus",partyDto);
			return result;
		}

		@Override
		public int joinDead(PartyDto partyDto) {
			int result = sqlsessionTemplate.update("party_mapper.joinDead",partyDto);
			return result;
		}

		@Override
		public int joinDelete(PartyDto partyDto) {
			int result = sqlsessionTemplate.update("party_mapper.joinDelete",partyDto);
			return result;
		}
		
	
	
	}

