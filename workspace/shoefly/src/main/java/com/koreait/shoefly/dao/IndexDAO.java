package com.koreait.shoefly.dao;

import java.util.List;

import com.koreait.shoefly.dto.Product;

public interface IndexDAO {

	
	public List<Product> selectRecommand();
	
	public List<Product> selectMostPopular();
	
	
	
}
