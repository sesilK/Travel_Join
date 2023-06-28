package com.app.dao.user.impl;

import com.app.dao.user.UserDao;
import com.app.dto.user.UserDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public UserDto insertImage(int imageId) {

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
