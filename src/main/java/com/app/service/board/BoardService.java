package com.app.service.board;

import java.util.List;

import com.app.dto.BoardDto;
import com.app.dto.PartyDto;

public interface BoardService {
		
	public BoardDto findPostById(String userId); //글상세 불러오기

	public List<PartyDto> getPartyMembersByPlanId(int planId); //plan 에 참가한 user들 조회

}
