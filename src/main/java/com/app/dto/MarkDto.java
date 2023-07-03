package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class MarkDto {

	int planId;			//여행번호
	int reviewId;		//리뷰번호
	String userId;		//아이디
	String sort;		//종류 (L:추천, R:신고)
	
}
