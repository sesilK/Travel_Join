package com.app.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.customer.UserDao;
import com.app.dto.user.UserDto;
import com.app.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public int saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		//if(userDto.getUserType()) {}
		
		int result = userDao.insertUser(userDto);
		
		return result;
	}

	@Override
	public List<UserDto> getUserList(UserDto userDto) {
		// TODO Auto-generated method stub
		
		List<UserDto> userList = userDao.selectUserList(userDto);
		
		return userList;
	}
}
