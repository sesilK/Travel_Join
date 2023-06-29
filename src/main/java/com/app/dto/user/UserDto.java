package com.app.dto.user;

import lombok.Data;

@Data
public class UserDto {

	//사용자 -> 공통부분 -> type(관리자/고객)
	public String id;
	public String name;
	public String tel;
	public String userType;  //ADM, CUS
	
}
