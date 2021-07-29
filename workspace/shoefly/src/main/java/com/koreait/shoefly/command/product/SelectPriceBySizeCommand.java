package com.koreait.shoefly.command.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ProductDAO;
import com.koreait.shoefly.dto.Member;
@Component
public class SelectPriceBySizeCommand implements ProductCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = (HttpSession)map.get("session");
		
		int productSize = Integer.parseInt(request.getParameter("productSize"));
		String productName = request.getParameter("productName");
		
		ProductDAO productDAO = sqlSession.getMapper(ProductDAO.class);
		Map<String, Object> resultMap = new HashMap<>();
		
		//본인이 올린 가격은 즉시구매가, 즉시즉시판매가 조회되지 않게하기 위해
		//비로그인경우와 로그인경우 를 구분함
		
		//비로그인시
		if((Member)session.getAttribute("loginMember") == null) {
			//즉시구매가격
			resultMap.put("buyPrice", productDAO.selectBuyPriceBySizeNoId(productSize, productName));
			//즉시판매가격
			resultMap.put("sellPrice", productDAO.selectSellPriceBySizeNoId(productSize, productName));
		}
		//로그인시
		else {
			Member loginMember = (Member)session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();			
			//즉시구매가격
			resultMap.put("buyPrice", productDAO.selectBuyPriceBySize(productSize, productName, memberId));
			//즉시판매가격
			resultMap.put("sellPrice", productDAO.selectSellPriceBySize(productSize, productName, memberId));
		}
		
		return resultMap;
	}

}
