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
import com.koreait.shoefly.util.SecurityUtils;

/**
 * 마이페이지에서 로그인한 아이디의 비밀번호를 수정하는 기능을 구현한 command
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class UpdatePwCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		String pw = request.getParameter("pw");
		long memberNo;
		if(loginMember == null) {
			memberNo = Long.parseLong(request.getParameter("memberNo"));			
		} else {
			memberNo = loginMember.getMemberNo();
		}
		
		Member member = new Member();
		member.setPw(SecurityUtils.sha256(pw));
		member.setMemberNo(memberNo);

		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.updatePw(member);		
		
		if(count > 0) {
			if(loginMember != null) {
				loginMember.setPw(SecurityUtils.sha256(pw));				
			}
		}
		
		return null;
	}

}
