package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Member;
import com.koreait.shoefly.util.SecurityUtils;

/**
 * 회원 비밀번호 변경 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class UpdateMemberPwManagerCommand implements ManagerCommand{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		Long memberNo = Long.parseLong(request.getParameter("memberNo"));
		String pw = SecurityUtils.createKey(12);
		String sha256_pw = SecurityUtils.sha256(pw);
		
		Member change_member = new Member();
		change_member.setMemberNo(memberNo);
		change_member.setPw(sha256_pw);
		
		int result = dao.updateMemberPw(change_member); // 비밀번호 변경
		
		if(result == 0) { // 오류 발생
			logger.error("회원번호 " + memberNo + "를 찾지 못하였습니다.");
			Map<String, Object> resultMap = new HashMap<>();
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('오류가 발생했습니다.');");
			sb.append("history.back();");
			sb.append("</script>");
			resultMap.put("response", sb.toString());
			return resultMap;
		}
		
		Member member = dao.selectOneMember(memberNo);
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setHeader("Content-Type", "text/plain; charset=utf-8");
			message.setFrom(new InternetAddress("yh30433583@gmail.com", "관리자"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(member.getEmail()));
			message.setSubject("SHOEFLY : 임시 비밀번호 발급입니다");
			StringBuilder sb = new StringBuilder();
			sb.append(member.getMemberId()).append("님의 비밀번호는").append("\n");
			sb.append(pw).append("\n");
			sb.append("꼭 비밀번호를 변경해 주세요.");
			message.setText(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mailSender.send(message);

		Map<String, Object> resultMap = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('정상적으로 비밀번호가 발급되었습니다.');");
		sb.append("location.href='memberInfoPage.do?no=" + memberNo + "';");
		sb.append("</script>");
		resultMap.put("response", sb.toString());
		
		return resultMap;
	}

}
