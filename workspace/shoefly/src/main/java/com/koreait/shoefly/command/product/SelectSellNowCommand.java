package com.koreait.shoefly.command.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ProductDAO;
import com.koreait.shoefly.dto.Member;
import com.koreait.shoefly.dto.MemberAddress;
import com.koreait.shoefly.dto.Product;
@Component
public class SelectSellNowCommand implements ProductCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		HttpSession session = (HttpSession)map.get("session");
		
		String productName = request.getParameter("productName");
		int productSize = Integer.parseInt(request.getParameter("productSize"));
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		ProductDAO productDAO = sqlSession.getMapper(ProductDAO.class);
		Product product = productDAO.buyApplication(productName, productSize);
		Long highPrice = productDAO.selectSellPriceBySize(productSize, productName, memberId);
		List<MemberAddress> addressList= productDAO.selectMemberAddr(memberId);
		
		model.addAttribute("product", product);
		model.addAttribute("highPrice", highPrice == null ? 0 : highPrice);
		model.addAttribute("addressList", addressList);

		return null;
	}

}
