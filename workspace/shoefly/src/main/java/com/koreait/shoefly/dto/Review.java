package com.koreait.shoefly.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Review {

	// 후기 일련번호 
    private long reviewNo;

    // 아이디 
    private String memberId;

    // 상품명 
    private String productName;

    // 제목 
    private String title;

    // 내용 
    private String content;

    // 이미지 
    private String image;

    // 작성일 
    private Date postdate;

    // 수정일 
    private Date modifydate;
    
    private int hit;

    // IP주소 
    private String ip;

    // 정상/삭제 
    private int state;

}
