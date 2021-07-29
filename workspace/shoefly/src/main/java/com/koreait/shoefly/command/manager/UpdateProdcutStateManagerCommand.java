package com.koreait.shoefly.command.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Product;

/**
 * 상품 거래 상태 변경 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class UpdateProdcutStateManagerCommand implements ManagerCommand{

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		String productNo = request.getParameter("no");
		int state = Integer.parseInt(request.getParameter("state"));
		
		Product product = new Product();
		product.setProductNo(productNo);
		product.setState(state);
		
		dao.updateProductState(product);
		
		return null;
	}
	
}
