package com.koreait.shoefly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Alarm {
	
	// 알람 일련번호 
    private long alarmNo;

    // 아이디 
    private String memberId;

    // 알람 내용 
    private String content;

    // 알림 읽음 여부 
    private int state;
    
}
