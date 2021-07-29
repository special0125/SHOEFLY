package com.koreait.shoefly.command.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.MemberAddress;

/**
 * 회원 주소 리스트 커맨드 
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectListMemberAddressManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		Long memberNo = Long.parseLong(request.getParameter("memberNo"));
		
		List<MemberAddress> list = dao.selectListMemberAddress(memberNo);

		model.addAttribute("list", list);
		return null;
	}

}
