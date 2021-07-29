package com.koreait.shoefly.command.member;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.controller.MemberController;
import com.koreait.shoefly.dao.MemberDAO;
import com.koreait.shoefly.dto.Member;
import com.koreait.shoefly.util.SecurityUtils;

/**
 * 로그인 기능을 구현한 command<br>
 * referer를 사용하여 로그인전 매핑값으로 이동한다.
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class LoginCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		response.setContentType("text/html; charset=utf-8");
		
		request.getSession().invalidate();
		
		String memberId = request.getParameter("memberId");
		String pw = request.getParameter("pw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPw(SecurityUtils.sha256(pw));
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		Member loginMember = memberDAO.login(member);
		
		String referer = request.getParameter("referer");
		if(referer == null || referer.isEmpty() || referer.indexOf("shoefly") == -1) {
			referer = "/shoefly/";
		} else if(referer.indexOf("/member/") != -1) {
			referer = "/shoefly/";
		}

		try {
			PrintWriter out = response.getWriter();
			
			if(loginMember != null) {
				request.getSession().setAttribute("loginMember", loginMember);
				out.println("<script>");
				out.println("location.href = '" + referer + "'");
				out.println("</script>");
			} else {
				out.println("<script>"); 			
				out.println("alert('아이디와 비밀번호를 확인해주세요.')"); 		
				out.println("location.href = 'loginPage.do'"); 		
				out.println("</script>"); 		
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}