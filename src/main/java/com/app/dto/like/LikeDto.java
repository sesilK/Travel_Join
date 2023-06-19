package com.app.dto.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class LikeDto {

	int reviewId;		//글번호
	String userId;		//작성자
}
