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
 * 회원 탈퇴 기능을 구현한 command<br>
 * 실제 탈퇴는 delete가 아닌 state 값을 update하는 방식
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class DeleteMemberCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		long memberNo = loginMember.getMemberNo();
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.deleteMember(memberNo);
		
		if(count > 0) {
			session.invalidate();
		}
		
		return null;
	}

}
