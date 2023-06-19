package com.app.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
public class CommentDto {

	int reviewId;			//글번호
	int commentId;			//댓글번호	
	String userId;			//작성자
	int parentCommentId;	//부모댓글번호
	String content;			//내용
	String createDate;		//작성일
	String updateDate;		//수정일
	String deleteAt;		//삭제여부
	String deleteDate;		//삭제일
}
