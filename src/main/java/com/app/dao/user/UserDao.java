package com.app.dao.user;

import java.util.List;

import com.app.dto.UserDto;

public interface UserDao {

	public int insertUser(UserDto userDto);
	
	public List<UserDto> selectUserList(UserDto userDto);
	
	public UserDto selectUserById(String user_id);

	public int update_user_info(UserDto userDto);
	
	public int selectUserCountById(String user_id);

	int update_user_profile(UserDto userDto);
}
