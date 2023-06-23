package com.app.dao.customer;

import com.app.dto.UserDto;

import java.util.List;

public interface UserDao {

	public int insertUser(UserDto userDto);
	
	public List<UserDto> selectUserList(UserDto userDto);
}
