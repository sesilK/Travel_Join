package com.app.service.board;

import java.util.List;

import com.app.dto.BoardDto;
import com.app.dto.JoinDto;
import com.app.dto.PartyDto;

public interface BoardService {
		
	public JoinDto findPostById(int planId); //글상세 불러오기

	public void joinParty(PartyDto partyDto);

	List<BoardDto> myTeamDetail(String userId); //plan 에 참가한 user들 조회

	

}
