package com.app.validator;

import org.springframework.validation.Errors;

import com.app.dto.UserDto;

import java.util.regex.Pattern;

public class UserValidator {

    static String idReg = "^[a-z]{1}[a-z0-9]{6,12}+$"; // 6~12자리 소문자+숫자
    static String pwReg = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,20}+$"; // 8~20자리 영,특
    static String emailReg = "^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]+$"; // 이메일 abc@abc.abc 형태
    static String nameReg = "^[ㄱ-ㅎ|가-힣]{2,6}$"; // 이름 한글 2~6길이
    static String nickReg = "^[0-9a-zA-Zㄱ-ㅎ가-힣]{2,10}$"; // 닉네임 영,한,숫자 2~10자리
    static String telReg = "^[\\d]{2,3}[\\d]{3,4}[\\d]{4}+$"; // 011-123-4567, 010-1234-5678 형태


    public static void registerValidate(Object target, Errors errors) {

        UserDto userDto = (UserDto) target;



        if (!Pattern.matches(idReg, userDto.getUserId())) {
            errors.rejectValue("userId", "userId", "아이디: 6~12자의 영문 소문자, 숫자만 사용 가능합니다.");
        }

        if (!Pattern.matches(pwReg, userDto.getPassword())) {
            errors.rejectValue("password", "password", "비밀번호: 8~20자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.");
        }

        if (!Pattern.matches(emailReg, userDto.getEmail())) {
            errors.rejectValue("email", "email", "이메일: 이메일 형식과 맞지 않습니다.");
        }

        if (!Pattern.matches(nameReg, userDto.getName())) {
            errors.rejectValue("name", "name", "이름: 이름 형식과 맞지 않습니다.");
        }

        if (!Pattern.matches(nickReg, userDto.getNick())) {
            errors.rejectValue("nick", "nick", "닉네임:  2~10자의 영문, 한글, 숫자만 사용 가능합니다.");
        }

        if (!Pattern.matches(telReg, userDto.getTel())) {
            errors.rejectValue("tel", "tel", "휴대폰번호: 번호 형식과 맞지 않습니다.");
        }
    }

    public static void modifyUserValidator(Object target, Errors errors) {

        UserDto userDto = (UserDto) target;

        if (!Pattern.matches(pwReg, userDto.getPassword())) {
            errors.rejectValue("password", "password", "비밀번호: 8~20자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.");
        }

        if (!Pattern.matches(emailReg, userDto.getEmail())) {
            errors.rejectValue("email", "email", "이메일: 이메일 형식과 맞지 않습니다.");
        }

        if (!Pattern.matches(nickReg, userDto.getNick())) {
            errors.rejectValue("nick", "nick", "닉네임:  2~10자의 영문, 한글, 숫자만 사용 가능합니다.");
        }

        if (!Pattern.matches(telReg, userDto.getTel())) {
            errors.rejectValue("tel", "tel", "휴대폰번호: 번호 형식과 맞지 않습니다.");
        }
    }
}