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
 * 아이디 존재 여부를 확인하는 기능을 구현한 command<br>
 * 아이디 찾기 기능 사용시 필요<br>
 * 이메일을 통해 state값이 0(정상)인 아이디의 존재 여부 확인
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class IdExistsCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String memberId = request.getParameter("memberId");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		String email = memberDAO.idExists(memberId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("email", email);
		
		return result;
	}

}
