package com.app.service.user;

import java.util.List;

import com.app.dto.user.UserDto;

public interface UserService {
	public int saveUser(UserDto userDto);
	
	//1.
	//public int saveCustomer(UserDto userDto);
	//public int saveAdmin(UserDto userDto);
	
	//2.
	//public int saveUser(UserDto userDto, boolean isAdmin);
	
	//3.
	//public int saveUser(UserDto userDto, String userCode); //"USR", "ADM"
	
	//4.
	//public int saveUser(UserDto userDto);  // userDto 객체 안에 typeCode 가 있을것이다.
	
	public List<UserDto> getUserList(UserDto userDto);
	//구분없이 전체 사용자 리스트
	//관리자 리스트  public List<UserDto> getAdminUserList();
	//고객 리스트    public List<UserDto> getCustomerUserList();

	List<UserDto> getUserList(String userId);
	
	//public List<UserDto> getUserList(String typeCode);  //"USR", "ADM", null
	
	//public List<UserDto> getUserList(UserDto userDto);  //userDto.typeCode
	
	
}

/*
서비스 insertCustomer -> 다오 insertCustomer

서비스 saveCustomer -> 다오 insertCustomer
   1. customer 테이블 기본정보저장
   2. customer_개인정보 동의내역 테이블
   3. customer_가입이력, 로그인 이력 테이블
*/
