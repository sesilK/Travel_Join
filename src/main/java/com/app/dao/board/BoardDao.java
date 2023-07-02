package com.app.dao.board;

import java.util.List;

import com.app.dto.BoardDto;
import com.app.dto.PartyDto;

public interface BoardDao {
		
	public BoardDto findPostById(int planId); //글상세 불러오기

	public void joinParty(PartyDto partyDto); //동행자 추가
	
	public List<BoardDto> myTeamDetail(String userId); //plan 에 참가한 user들 조회

	public void plusView(int planId);



}
