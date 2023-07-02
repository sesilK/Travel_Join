package com.app.dto.user;

import lombok.Data;

@Data
public class UserDto {
	public String userId;
	public String password;
	public String email;
	public String name;
	public String nick;
	public String tel;
	public String birth;
	public String gender;
	public int status;
	public String joinDate;
	public int rank;
	public int imageId; //사진고유번호

	public int age;	// DB에는 없는 항목, 편의성을 위해 추가
}