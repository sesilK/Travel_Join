package com.app.dao.party;

import com.app.dto.party.PartyDto;

public interface PartyDao {

	
	public PartyDto joinParty(String userId); // 동행모집
	
	public PartyDto myTeamDetail();		// 여행팀 멤버 조회
	
	public PartyDto addMember(int no); // 팀원 업데이트
}
