package com.koreait.shoefly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberAddress {

	// 주소일련번호 
    private long memberAddressNo;

    // 회원일련번호 
    private long memberNo;

    // 이름 
    private String name;
    
    // 주소 
    private String addr1;

    // 상세주소 
    private String addr2;

    // 정상/삭제 
    private int state;
    
}
