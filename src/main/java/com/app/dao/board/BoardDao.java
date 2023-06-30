package com.app.dao.board;

import java.util.List;

import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;

public interface BoardDao {
		
	/*
	 * public BoardDto findPostById(String userId); //글상세 불러오기
	 */
	public List<PartyDto> getPartyMembersByPlanId(int planId); //plan 에 참가한 user들 조회

	public BoardDto findPostById(int planId);

	public void plusView(int planId);



}
