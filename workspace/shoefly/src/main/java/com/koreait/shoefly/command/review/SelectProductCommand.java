package com.koreait.shoefly.command.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ProductDAO;
import com.koreait.shoefly.dao.ReviewDAO;
import com.koreait.shoefly.dto.Product;

@Component
public class SelectProductCommand implements ReviewCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		
		ReviewDAO reviewDAO = sqlSession.getMapper(ReviewDAO.class);
		
		List<Product> productName = reviewDAO.selectProduct();
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("productName", productName);
		
		return resultMap;
	}

}
