package com.app.dao.user.impl;

import com.app.dao.user.UserDao;
import com.app.dto.UserDto;
import com.app.validator.UserValidator;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    // 회원가입
    @Override
    public int insertUser(UserDto userDto) {
        int result = 0;

        try {
            result = sqlSessionTemplate.insert("user_mapper.insert_user", userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    public int selectUserCountById(String user_id) {
        int result = sqlSessionTemplate.selectOne("user_mapper.select_user_count_by_id", user_id);
        System.out.println(user_id);
        return result;
    }

    @Override
    public int update_user_profile(UserDto userDto) {
        int result = 0;
        result = sqlSessionTemplate.update("user_mapper.update_user_profile", userDto);
        return result;
    }

    @Override
    public int update_user_status(UserDto userDto) {
        int result = 0;
        result = sqlSessionTemplate.update("user_mapper.update_user_status", userDto);
        return result;
    }

    @Override
    public int update_user_info(UserDto userDto, BindingResult bindingResult) {

        UserValidator.modifyUserValidator(userDto, bindingResult);
        if(bindingResult.hasErrors()) {
            return -1;
        }

        int result = sqlSessionTemplate.update("user_mapper.update_user_info", userDto);
        return result;
    }
}
