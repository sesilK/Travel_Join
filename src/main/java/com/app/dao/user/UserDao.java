package com.app.dao.user;

import com.app.dto.user.UserDto;

import java.util.List;

public interface UserDao {

	public int insertUser(UserDto userDto);
	
	public List<UserDto> selectUserList(UserDto userDto);
	
	public UserDto selectUserById(String user_id);

	public int update_user_info(UserDto userDto);
	
	public int selectUserCountById(String user_id);
	
	public UserDto insertImage(int imageId);

}
