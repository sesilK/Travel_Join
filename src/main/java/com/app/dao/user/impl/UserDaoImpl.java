package com.app.dao.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.user.UserDao;
import com.app.dto.user.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	// 회원가입
	@Override
	public int insertUser(UserDto userDto) {

		int result = sqlSessionTemplate.insert("user_mapper.insert_user", userDto);
		return result;
	}

	@Override
	public List<UserDto> selectUserList(UserDto userDto) {

		return null;
	}

	@Override
	public UserDto selectUserById(String user_id) {
		UserDto userDto = sqlSessionTemplate.selectOne("user_mapper.select_user_by_id", user_id);
		return userDto;
	}

	@Override
	public int update_user_info(UserDto userDto) {
		// TODO Auto-generated method stub
		int result = sqlSessionTemplate.update("user_mapper.update_user_info", userDto);
		return result;
	}


	
}
