package com.app.dao.board;

import com.app.dto.BoardDto;
import com.app.dto.JoinDto;
import com.app.dto.PartyDto;

import java.util.List;

public interface BoardDao {
		
	public JoinDto findPostById(int planId); //글상세 불러오기

	public void joinParty(PartyDto partyDto); //동행자 추가
	
	public List<BoardDto> myTeamDetail(String userId); //plan 에 참가한 user들 조회

	List<JoinDto> select_images_by_planId(int planId);
}
