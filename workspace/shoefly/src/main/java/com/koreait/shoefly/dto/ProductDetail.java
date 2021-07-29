package com.koreait.shoefly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProductDetail {
	
	// 상품 일련번호 
    private long productDetailNo;

    // 상품이름 
    private String productName;

    // 사이즈 
    private int productSize;
    
}
