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
	
	@Override
	public List<JoinDto> JoinView() {
		// TODO Auto-generated method stub
		
		List<JoinDto> JoinView =
						sqlSessionTemplate.selectList("join_mapper.select_GroupBuying");
		
		return JoinView;
	}

}
