package com.koreait.shoefly.command.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Member;

/**
 * 회원 상세보기 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectOneMemberManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		long memberNo = Long.parseLong(request.getParameter("no"));
		Member member = dao.selectOneMember(memberNo);
		
		model.addAttribute("member", member);
		return null;
	}
	
}
