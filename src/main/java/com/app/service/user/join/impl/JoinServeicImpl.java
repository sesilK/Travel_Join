package com.app.service.user.join.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.join.JoinDao;
import com.app.dto.JoinDto;
import com.app.service.user.join.JoinService;



@Service
public class JoinServeicImpl implements JoinService{

	@Autowired
	JoinDao joinDao;
//////////////////////////////////////////////////////////////
//  /join_view controller 호출메소드	
	
	@Override
	public List<JoinDto> JoinViews() {
		// TODO Auto-generated method stub
		
		List<JoinDto> joinviews = joinDao.JoinView();
		
		return joinviews;
	}
	@Override
	public List<JoinDto> imageFileName() {
		List<JoinDto> imageFileName = joinDao.imageFileName();
		return imageFileName;
	}
	
//////////////////////////////////////////////////////////////
	@Override
	public int boardMaking(JoinDto joinDto) {
		// TODO Auto-generated method stub
		int makingResult = joinDao.makingboard(joinDto);
		return makingResult;
	}

	@Override
	public int getBoardNum(JoinDto joinDto) {
		// TODO Auto-generated method stub
		
		int getBoardNum = joinDao.getBoardNum(joinDto);
		return getBoardNum;
	}

	@Override
	public int boardImgList(JoinDto joinDto) {
		// TODO Auto-generated method stub
		int boardImgList = joinDao.boardImgList(joinDto);
		return boardImgList;
	}




	
}
