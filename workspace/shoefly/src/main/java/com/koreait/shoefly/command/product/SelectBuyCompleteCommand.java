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
import com.koreait.shoefly.dto.MemberAddress;
@Component
public class SelectBuyCompleteCommand implements ProductCommand {
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = (HttpSession)map.get("session");
		
		long MaxproductBuyNo = Long.parseLong(request.getParameter("MaxproductBuyNo"));
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();

		ProductDAO productDAO = sqlSession.getMapper(ProductDAO.class);
		
		long productDetailNo = productDAO.selectProductBuyByNo(MaxproductBuyNo);
		String productName = productDAO.selectProductDetailInfo1(productDetailNo);
		int productSize = productDAO.selectProductDetailInfo2(productDetailNo);
		long price = productDAO.selectPriceinBuy(MaxproductBuyNo);
		long memberAddressNo = productDAO.selectMemberAddrNoInBuy(MaxproductBuyNo);
		MemberAddress memberAddress = productDAO.selectMemberAddrByProductSellNo(memberAddressNo);
		
		Map<String, Object> resultMap = new HashMap<>();
		map.put("memberId", memberId);
		map.put("productName", productName);
		map.put("productSize", productSize);
		map.put("price", price);
		map.put("memberAddress", memberAddress);
		model.addAttribute("resultMap", resultMap);
		
		return null;
	}
}
