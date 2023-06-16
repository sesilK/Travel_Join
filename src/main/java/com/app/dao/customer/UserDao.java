package com.app.dao.customer;

import java.util.List;

import com.app.dto.user.UserDto;

public interface UserDao {

	public int insertUser(UserDto userDto);
	
	public List<UserDto> selectUserList(UserDto userDto);
}
