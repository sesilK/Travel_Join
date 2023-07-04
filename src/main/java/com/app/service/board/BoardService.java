package com.app.service.board;

import com.app.dto.JoinDto;
import com.app.dto.MarkDto;
import com.app.dto.PartyDto;

import java.util.List;

public interface BoardService {
		
	public JoinDto findPostById(int planId); //글상세 불러오기

	public void joinParty(PartyDto partyDto);

	List<JoinDto> myTeamDetail(String userId); //plan 에 참가한 user들 조회

	List<JoinDto> travelTogether(String userId);
	
	List<JoinDto> myPartying(String userId);
	List<JoinDto> select_images_by_planId(int planId);

	public int joinBoardViewIncrease(int planId); //조회수 증가
	
	public MarkDto selectBoardMark(int planId, String userId, String sort); //추천여부 확인
	
	public int insertBoardMark(int planId, String userId, String sort); //추천하기(횟수반환)

}
