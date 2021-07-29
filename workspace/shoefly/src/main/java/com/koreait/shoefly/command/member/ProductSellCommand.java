package com.koreait.shoefly.command.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.controller.MemberController;
import com.koreait.shoefly.dao.MemberDAO;
import com.koreait.shoefly.dto.Member;
import com.koreait.shoefly.dto.ProductSell;

/**
 * 마이페이지의 판매내역 리스트를 출력하는 기능을 구현한 command<br>
 * 판매내역 리스트(판매중, 판매대기, 판매확정, 거래완료)
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class ProductSellCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();

		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		List<ProductSell> saleList0 = memberDAO.selectListSell(0, memberId);
		List<ProductSell> saleList1 = memberDAO.selectListSell(1, memberId);
		List<ProductSell> saleList2 = memberDAO.selectListSell(2, memberId);
		List<ProductSell> saleList3 = memberDAO.selectListSell(3, memberId);
		
		model.addAttribute("sellRequestList", saleList0);
		model.addAttribute("waitingForSale", saleList1);
		model.addAttribute("soldOut", saleList2);
		model.addAttribute("sellCompleted", saleList3);
		
		return null;
	}

}
