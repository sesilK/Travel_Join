package com.app.validator;

import java.util.regex.Pattern;

public class TestMain {
    public static void main(String[] args) {

        String idReg = "^[a-z]{1}[a-z0-9]{6,12}+$"; // 6~12자리 소문자+숫자
        String pwReg = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,20}+$"; // 8~20자리 영,특
        String emailReg = "^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]+$"; // 이메일 abc@abc.abc 형태
        String nameReg = "^[가-힣]{2,6}$"; // 이름 한글 2~6길이
        String nickReg = "^[0-9a-zA-Zㄱ-ㅎ가-힣]{2,10}$"; // 닉네임 영,한,숫자 2~10자리
        String telReg = "^[\\d]{2,3}[\\d]{3,4}[\\d]{4}+$";; // 011-123-4567, 010-1234-5678 형태

        System.out.println(Pattern.matches(nameReg, "ㄱㅁㄴ"));
        ;
    }
}
