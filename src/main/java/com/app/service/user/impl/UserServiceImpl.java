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
        userDto.setGender(parseGender(birth));    // gender 채우기
        userDto.setBirth(parseBirth(birth));    // 뒷자리 버리기
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
        String birth_back = birth.substring(length - 1, length);    // 주민 뒷자리 1개 가져오기
        int birth_back_int = Integer.parseInt(birth_back) % 2;  // 뒷자리가 짝수면 0, 홀수면 1

        return birth_back_int == 1 ? "M" : "F";
    }

    /** 주민번호 앞자리만 뽑아내는 메소드 */
    public String parseBirth(String birth) {
        int length = birth.length();
        String birth_after = birth.substring(0, length - 1); // 뒷자리 1개 날리기

        return birth_after;
    }
}
