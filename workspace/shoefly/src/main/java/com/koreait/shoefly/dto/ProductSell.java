package com.koreait.shoefly.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProductSell {

	// 판매 일련번호 
    private long productSellNo;

    // 아이디 
    private String memberId;

    // 상품 일련번호 
    private long productDetailNo;

    // 판매가격 
    private long price;

    // 판매자주소 
    private long memberAddressNo;

    // 판매등록일자 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private Date postdate;

    // 판매완료일자 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private Date selldate;

    // 구매 일련번호 
    private int productBuyNo;

    // 등록/취소/판매완료 
    private int state;

    
    // 추가칼럼
    // 상품 이름
    private String productName;
    
    // 이미지
    private String image;
    
    // 사이즈 
    private int productSize;
    
    // 상품번호 
    private String productNo;
    
}
