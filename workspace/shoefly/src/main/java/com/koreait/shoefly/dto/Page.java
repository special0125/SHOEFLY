package com.koreait.shoefly.dto;

import com.koreait.shoefly.util.PagingUtils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Paging 작업을 위한 DTO
 * 
 * @see PagingUtils
 * @author 박세환
 */
@Data
@NoArgsConstructor
public class Page {
	private int page;
	private int totalRecord;
	private int recordPerPage;
	private int beginRecord;
	private int endRecord;
	private int totalPage;
	private int pagePerBlock;
	private int beginPage;
	private int endPage;
}