package com.app.dao.join.impl;

import java.util.List; 


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.join.JoinDao;
import com.app.dto.join.JoinDto;





@Repository
public class JoinDaoImpl implements JoinDao{
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

////////////////////////////////////////////////////////////////////////////////////////////////
// join_view 호출 메소드	
	
	@Override
	public List<JoinDto> JoinView() {
		// TODO Auto-generated method stub
		
		List<JoinDto> JoinView =
						sqlSessionTemplate.selectList("join_mapper.select_all_JoinDto_info");		
		return JoinView;
	}

	/*
	 * @Override public List<JoinDto> imageFileName() { List<JoinDto> imageFileName
	 * = sqlSessionTemplate.selectList("join_mapper.select_JoinDto_imageFileName");
	 * return imageFileName; }
	 */

//////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int getBoardNum(JoinDto joinDto) {
		// TODO Auto-generated method stub
		int getBoardNum =
				sqlSessionTemplate.selectOne("join_mapper.select_JoinDto_id",joinDto);		
		return getBoardNum;
	}

	@Override
	public int makingboard(JoinDto joinDto) {
		// TODO Auto-generated method stub
		int makingResult = sqlSessionTemplate.insert("join_mapper.insert_join",joinDto);
		return makingResult;
	}


	@Override
	public int boardImgList(JoinDto joinDto) {
		// TODO Auto-generated method stub
		
		int boardImgList = sqlSessionTemplate.insert("join_mapper.insert_join_imageFileName", joinDto);
		
		return boardImgList;
	}


	

	

	
}
