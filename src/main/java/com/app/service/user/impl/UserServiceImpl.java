package com.app.service.user.impl;

import com.app.dao.user.UserDao;
import com.app.dto.user.UserDto;
import com.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public int saveUser(UserDto userDto) {
		int result = userDao.insertUser(userDto);
		return result;
	}

	@Override
	public List<UserDto> getUserList(UserDto userDto) {
		return null;
	}


	@Override
	public boolean login(UserDto userDto) {

		// 입력받은 아이디를 기준으로 DB에서 회원정보 조회
		UserDto findUser = userDao.selectUserById(userDto.getUserId());

		String input_pw = userDto.getPassword(); // form 에서 입력한 pw
		String db_pw = findUser.getPassword();	// db에서 찾은 pw

		if (findUser != null) { // 찾은 회원정보가 null 이 아니면
			if (input_pw.equals(db_pw)) { // 입력한 pw와 회원정보의 pw가 일치하면
				return true;
			}
		}

		return false;
	}

	/** 아이디 중복체크 */
	@Override
	public boolean idCheck(String userId) {

		int result = userDao.selectUserCountById(userId);
		// 조회되는 아이디가 없으면 0임
		if(result == 0) {
			return true;
		}

		return false;
	}

}
