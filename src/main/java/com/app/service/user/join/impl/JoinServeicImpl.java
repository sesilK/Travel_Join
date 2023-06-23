package com.app.service.user.join.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.join.JoinDao;
import com.app.dto.join.JoinDto;
import com.app.service.user.join.JoinService;

@Service
public class JoinServeicImpl implements JoinService{

	@Autowired
	JoinDao joinDao;

	@Override
	public List<JoinDto> JoinViews() {
		// TODO Auto-generated method stub
		
		List<JoinDto> joinviews = joinDao.JoinView();
		
		return joinviews;
	}
}
