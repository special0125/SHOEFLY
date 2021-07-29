package com.koreait.shoefly.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductBuy {

	// 구매 일련번호 
    private long productBuyNo;

    // 아이디 
    private String memberId;

    // 상품 일련번호 
    private long productDetailNo;

    // 구매가격 
    private long price;

    // 구매자주소 
    private long memberAddressNo;

    // 구매등록일자 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private Date postdate;

    // 구매완료일자 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private Date buydate;

    // 등록/취소/구매완료 
    private int state;
    
    // 이미지
    private String image;
    
    // 상품이름 
    private String productName;

    // 사이즈 
    private int productSize;
    
    // 상품번호 
    private String productNo;
    
}
