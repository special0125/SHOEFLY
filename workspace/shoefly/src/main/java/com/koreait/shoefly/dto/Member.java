package com.koreait.shoefly.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {

	// 회원일련번호 
    private long memberNo;

    // 아이디 
    private String memberId;

    // 비밀번호 
    private String pw;

    // 이름 
    private String name;

    // 이메일 
    private String email;

    // 가입일 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regdate;

    // 관리자여부 
    private int manager;

    // 정상/탈퇴 
    private int state;
    
}
