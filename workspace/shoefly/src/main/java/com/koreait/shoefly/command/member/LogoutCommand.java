package com.koreait.shoefly.command.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.controller.MemberController;

/**
 * 로그아웃 기능을 구현한 command
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class LogoutCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		
		if(session.getAttribute("loginMember") != null) {
			session.invalidate();
		}
		
		return null;
	}

}
