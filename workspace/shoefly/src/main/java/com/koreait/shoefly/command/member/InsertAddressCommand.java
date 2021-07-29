package com.koreait.shoefly.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.controller.MemberController;
import com.koreait.shoefly.dao.MemberDAO;
import com.koreait.shoefly.dto.Member;
import com.koreait.shoefly.dto.MemberAddress;

/**
 * 마이페이지 주소록에 새로운 주소를 추가하는 기능을 구현한 command
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class InsertAddressCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String name = request.getParameter("name");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		long memberNo = loginMember.getMemberNo();
		
		MemberAddress memberAddress = new MemberAddress();
		memberAddress.setName(name);
		memberAddress.setAddr1(addr1);
		memberAddress.setAddr2(addr2);
		memberAddress.setMemberNo(memberNo);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);	
		memberDAO.insertAddress(memberAddress);
		
		return null;
	}

}
