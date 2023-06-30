package com.app.dao.join;

import java.util.List;
import com.app.dto.join.JoinDto;


public interface JoinDao {

	List<JoinDto> JoinView();
	
//	List<JoinDto> imageFileName();

	int makingboard(JoinDto joinDto);
	
	int getBoardNum(JoinDto joinDto);

	int boardImgList(JoinDto joinDto);

}
