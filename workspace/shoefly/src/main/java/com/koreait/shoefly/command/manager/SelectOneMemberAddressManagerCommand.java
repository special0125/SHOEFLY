package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.MemberAddress;

/**
 * 회원주소 상세보기 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectOneMemberAddressManagerCommand implements ManagerCommand{

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		long memberAddressNo = Long.parseLong(request.getParameter("no"));
		MemberAddress memberAddress = dao.selectOneMemberAddress(memberAddressNo);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", memberAddress != null);
		resultMap.put("addr", memberAddress);
		
		return resultMap;
	}
	
}
