package com.koreait.shoefly.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Faq {

	// FAQ게시글 일련번호 
    private long faqNo;

    // 아이디 
    private String memberId;

    // FAQ 제목 
    private String question;

    // FAQ 내용 
    private String answer;

    // FAQ 작성일 
    private Date postdate;
    
}
