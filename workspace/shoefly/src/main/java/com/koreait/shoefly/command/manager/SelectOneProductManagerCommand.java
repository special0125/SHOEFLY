package com.koreait.shoefly.command.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Product;
import com.koreait.shoefly.dto.ProductDetail;

/**
 * 상품 상세보기 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectOneProductManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		String productNo = request.getParameter("no");
		
		Product product = dao.selectOneProdcut(productNo);
		model.addAttribute("product", product);
		
		List<ProductDetail> details = dao.selectListProductDetail(product.getProductName());
		int size = details.size();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(details.get(i).getProductSize());
			if(i != size - 1)
				sb.append(", ");
		}
		
		model.addAttribute("sizes", sb.toString());
		return null;
	}
	
}
