package com.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PartyDto {
	int planId;			// 게시글 번호 (조회용)
	String userId; //참가인원  참가할 아이디
	

	
}
