package com.app.dao.board;

import java.util.List;

import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;

public interface BoardDao {
		
<<<<<<< HEAD
	public BoardDto findPostById(int planId); //글상세 불러오기

	public void joinParty(PartyDto partyDto); //동행자 추가
	
	public List<BoardDto> myTeamDetail(String userId); //plan 에 참가한 user들 조회

=======
	/*
	 * public BoardDto findPostById(String userId); //글상세 불러오기
	 */
	public List<PartyDto> getPartyMembersByPlanId(int planId); //plan 에 참가한 user들 조회
>>>>>>> dcb72b8b0c0a421d62435665e8382dc0b8dd380b

	public BoardDto findPostById(int planId);

	public void plusView(int planId);



}
