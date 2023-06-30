package com.app.dao.party;

import java.util.List;

import com.app.dto.party.PartyDto;

public interface PartyDao {

	
	public int joinParty(PartyDto partyDto); // 동행모집
	
	public List<PartyDto> myTeamDetail(int no);		// 여행팀 멤버 조회
	
	public int addMember(int no); // 팀원 업데이트
	
	public int checkStatus(PartyDto partyDto); //여행 참가여부 조회
	
	public int joinDead(PartyDto partyDto); //모집 마감
	
	public int joinDelete(PartyDto partyDto); //모집글 삭제
}
