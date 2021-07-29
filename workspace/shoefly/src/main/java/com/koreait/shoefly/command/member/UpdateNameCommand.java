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

/**
 * 마이페이지의 로그인한 아이디의 이름을 수정하는 기능을 구현한 command
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class UpdateNameCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
		Member loginMember = (Member)session.getAttribute("loginMember");
		long memberNo = loginMember.getMemberNo();
		Member member = new Member();
		member.setName(name);
		member.setMemberNo(memberNo);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.updateName(member);
		if(count > 0) {
			if(loginMember != null) {
				loginMember.setName(name);			
			}
		}
		
		return null;
	}

}
