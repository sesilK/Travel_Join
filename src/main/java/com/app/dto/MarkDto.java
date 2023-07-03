package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class MarkDto {

	int reviewId;		//글번호
	String userId;		//아이디
	String sort;		//종류
	
}
