package com.app.service.board;

import java.util.List;

import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;

public interface BoardService {
		
	public BoardDto findPostById(int planId); //글상세 불러오기

	public void joinParty(PartyDto partyDto); 

	public void plusView(int planId);

	List<BoardDto> myTeamDetail(String userId); //plan 에 참가한 user들 조회

	

}
