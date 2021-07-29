package com.koreait.shoefly.command.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.IndexDAO;
import com.koreait.shoefly.dto.Product;

@Component
public class SelectRecommandCommand implements IndexCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		IndexDAO indexDAO = sqlSession.getMapper(IndexDAO.class);
		
		List<Product> recommandProduct = indexDAO.selectRecommand();
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("recommandProduct", recommandProduct);
		
		return resultMap;
	}

}
