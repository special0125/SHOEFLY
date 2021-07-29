package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.ProductBuy;

/**
 * 상품구매신청 STATE 변경 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class UpdateProductBuyStateManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		long productBuyNo = Long.parseLong(request.getParameter("no"));
		int state = Integer.parseInt(request.getParameter("state"));
		
		ProductBuy productBuy = new ProductBuy();
		productBuy.setProductBuyNo(productBuyNo);
		productBuy.setState(state);
		
		int result = dao.updateProductBuyState(productBuy);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result > 0);
		
		return resultMap;
	}
	
}
