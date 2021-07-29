package com.koreait.shoefly.command.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.controller.MemberController;
import com.koreait.shoefly.dao.MemberDAO;

/**
 * 아이디 중복 여부를 확인하는 기능을 구현한 command
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class IdCheckCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String memberId = request.getParameter("memberId");
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		Map<String, Object> result = new HashMap<>();
		result.put("count", memberDAO.idCheck(memberId));
		
		return result;
	}

}
