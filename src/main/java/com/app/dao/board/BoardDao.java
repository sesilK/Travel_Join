package com.app.dao.board;

import com.app.dto.BoardDto;
import com.app.dto.JoinDto;
import com.app.dto.PartyDto;
import com.app.dto.MarkDto;

import java.util.List;




public interface BoardDao {
		
	public JoinDto findPostById(int planId); //글상세 불러오기

	public void joinParty(PartyDto partyDto); //동행자 추가
	
<<<<<<< HEAD
	public List<BoardDto> myTeamDetail(String userId); //plan 에 참가한 user들 조회
	
	public List<JoinDto> travelTogether(String userId);

	public List<JoinDto> myPartying(String userId);
=======
	public List<JoinDto> myTeamDetail(String userId); //plan 에 참가한 user들 조회
	
	public int joinBoardViewIncrease(int planId); //조회수 증가
	
	public MarkDto selectBoardMark(int planId, String userId, String sort); //추천여부 확인
	
	public int insertBoardMark(int planId, String userId, String sort); //추천하기(횟수반환)

	List<JoinDto> select_images_by_planId(int planId);
>>>>>>> 055e214ee22e0fedf79d05fece329e00268096c7
}
