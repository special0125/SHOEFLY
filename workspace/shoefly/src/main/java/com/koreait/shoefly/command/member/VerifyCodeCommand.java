package com.koreait.shoefly.command.member;

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

import com.koreait.shoefly.controller.MemberController;
import com.koreait.shoefly.util.SecurityUtils;

/**
 * 이메일 인증을 위한 인증코드를 전송하는 기능을 구현한 command
 * 
 * @author 정유한
 * @see MemberController
 */
@Component
public class VerifyCodeCommand implements MemberCommand {

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String email = request.getParameter("email");
		String authCode = null;
		
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setHeader("Content-Type", "text/plain; charset=utf-8");
			message.setFrom(new InternetAddress("yh30433583@gmail.com", "관리자"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("인증 요청 메일입니다.");
			authCode = SecurityUtils.createKey(6);  // 6자리 인증코드
			message.setText("인증번호는 " + authCode + "입니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mailSender.send(message);
		
		Map<String, Object> result = new HashMap<>();
		result.put("authCode", authCode);
		return result;
		
	}

}
