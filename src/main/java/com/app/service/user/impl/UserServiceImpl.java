package com.app.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.user.UserDao;
import com.app.dto.user.UserDto;
import com.app.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public int saveUser(UserDto userDto) {
		String birth = userDto.getBirth();
		userDto.setGender(parseGender(birth)); // gender 채우기
		userDto.setBirth(parseBirth(birth)); // 뒷자리 버리기
		int result = userDao.insertUser(userDto);

		return result;
	}

	@Override
	public List<UserDto> getUserList(UserDto userDto) {
		return null;
	}

	/** 주민번호 뒷자리로 성별 구분하는 메소드 */
	public String parseGender(String birth) {
		int length = birth.length();
		String birth_back = birth.substring(length - 1, length); // 주민 뒷자리 1개 가져오기
		int birth_back_int = Integer.parseInt(birth_back) % 2; // 뒷자리가 짝수면 0, 홀수면 1

		return birth_back_int == 1 ? "M" : "F";
	}

	/** 주민번호 앞자리만 뽑아내는 메소드 */
	public String parseBirth(String birth) {
		int length = birth.length();
		String birth_after = birth.substring(0, length - 1); // 뒷자리 1개 날리기

		return birth_after;
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

	//회원정보수정
	@Override
	public int updateUser(UserDto userDto) {
		// TODO Auto-generated method stub
	
		int result= userDao.update_user_info(userDto);
		
		return result;
	}

	//id로 회원정보 조회
	@Override
	public UserDto getUserInfo(String userId) {
		
		UserDto userInfo = userDao.selectUserById(userId);

		return userInfo;
	}

}
