package com.app.service.party;

import java.util.List;

import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;

public interface PartyService {
	
	public PartyDto joinParty(BoardDto boardDto); // 동행신청
	
//	List<PartyDto> myTeamDetail(int planId); // 여행팀 멤버 조회
	
	public PartyDto addMember(int no); // 팀원 업데이트

}
