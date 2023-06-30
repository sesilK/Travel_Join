package com.app.service.board;

import java.util.List;

import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;

public interface BoardService {
		
	public BoardDto findPostById(int planId); //글상세 불러오기
	
	public void plusView(int planId);

	public List<PartyDto> getPartyMembersByPlanId(int planId); //plan 에 참가한 user들 조회

}
