package com.koreait.shoefly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ReviewComment {

	// 후기 댓글 일련번호 
    private long reviewCommentNo;

    // 후기 일련번호 
    private long reviewNo;

    // 아이디 
    private String memberId;

    // 내용 
    private String context;

    // 정상/삭제 
    private int state;
    
}
