package com.app.dto;

import lombok.Data;

@Data
public class UserDto {

	//사용자 -> 공통부분 -> type(관리자/고객)
	public String id;
	public String name;
	public String userType;  //ADM, CUS
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getUserType() {
//		return userType;
//	}
//
//	public void setUserType(String userType) {
//		this.userType = userType;
//	}
	
	
}
