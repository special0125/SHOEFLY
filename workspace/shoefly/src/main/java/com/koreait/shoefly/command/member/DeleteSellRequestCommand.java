package com.koreait.shoefly.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.controller.MemberController;
import com.koreait.shoefly.dao.MemberDAO;

/**
 * myPage 판매내역에서 판매중인 상품 삭제 기능 command<br>
 * 실제 삭제는 delete가 아닌 state 값을 update하는 방식
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class DeleteSellRequestCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long productSellNo = Long.parseLong(request.getParameter("productSellNo"));
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		memberDAO.deleteSellRequest(productSellNo);
		
		return null;
	}

}
