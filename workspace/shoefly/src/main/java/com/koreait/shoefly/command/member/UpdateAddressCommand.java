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
 * 마이페이지 주소록에 저장된 주소를 수정하는 기능을 구현한 command
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class UpdateAddressCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		long memberNo = loginMember.getMemberNo();
		
		MemberAddress memberAddress = new MemberAddress();
		
		memberAddress.setMemberAddressNo(Long.parseLong(request.getParameter("memberAddressNo")));
		memberAddress.setMemberNo(memberNo);
		memberAddress.setName(request.getParameter("name"));
		memberAddress.setAddr1(request.getParameter("addr1"));
		memberAddress.setAddr2(request.getParameter("addr2"));
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		memberDAO.updateAddress(memberAddress);
				
		return null;
	}

}
