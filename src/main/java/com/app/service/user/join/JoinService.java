package com.app.service.user.join;

import java.util.List;




import com.app.dto.JoinDto;




public interface JoinService {

	List<JoinDto> JoinViews();
	
//	List<JoinDto> imageFileName();
	
	int boardMaking(JoinDto joinDto);
	
	int getBoardNum(JoinDto joinDto);

	int boardImgList(JoinDto joinDto);


}
