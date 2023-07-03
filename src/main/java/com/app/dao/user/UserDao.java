package com.app.dao.user;

import com.app.dto.UserDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserDao {

	public int insertUser(UserDto userDto);
	
	public List<UserDto> selectUserList(UserDto userDto);
	
	public UserDto selectUserById(String user_id);

	public int update_user_info(UserDto userDto, BindingResult bindingResult);
	
	public int selectUserCountById(String user_id);

	int update_user_profile(UserDto userDto);

	int update_user_status(UserDto userDto);
}
