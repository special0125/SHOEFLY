package com.koreait.shoefly.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.controller.MemberController;
import com.koreait.shoefly.dao.MemberDAO;
import com.koreait.shoefly.dto.MemberAddress;

/**
 * 수정할 주소의 정보를 출력하는 기능을 구현한 command
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class UpdateAddressListCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long memberAddressNo = Long.parseLong(request.getParameter("memberAddressNo"));
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		MemberAddress memberAddress = memberDAO.selectAddrByNo(memberAddressNo);
		
		if(memberAddress != null) {
			model.addAttribute("memberAddress", memberAddress);
		}
		
		return null;
	}

}
