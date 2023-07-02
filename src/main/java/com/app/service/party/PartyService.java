package com.app.service.party;

import java.util.List;

import com.app.dto.BoardDto;
import com.app.dto.PartyDto;

public interface PartyService {
	
	public int joinParty(PartyDto partyDto); // 동행신청
	
	public List<PartyDto> myTeamDetail(int no);		// 여행팀 멤버 조회
	
	public int addMember(int no); // 팀원 업데이트
	
	public int checkStatus(PartyDto partyDto); //여행 참가여부 조회
	
	public int joinDead(PartyDto partyDto); //모집 마감
	
	public int joinDelete(PartyDto partyDto); //모집글 삭제
}
