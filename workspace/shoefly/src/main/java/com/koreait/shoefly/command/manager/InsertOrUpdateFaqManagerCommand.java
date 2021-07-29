package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Faq;
import com.koreait.shoefly.dto.Member;

/**
 * FAQ 작성 및 수정 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class InsertOrUpdateFaqManagerCommand implements ManagerCommand {

	private Map<String, Object> resultMap;
	private HttpServletRequest request;
	private ManagerDAO dao;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		this.dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		this.request = (HttpServletRequest)modelMap.get("request");
		this.resultMap = new HashMap<>();
		
		String strFaqNo = request.getParameter("faqNo");
		if(strFaqNo == null || strFaqNo.isEmpty()) {
			insertNotice();  // strNoticeNo값이 없으면
		} else {
			updateNotice();  // strNoticeNo값이 있으면
		}
		
		return resultMap;
	}

	/**
	 * strFaqNo값이 없으면 새글 추가
	 */
	private void insertNotice() {
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		String id = loginMember.getMemberId();
		
		Faq faq = new Faq();
		faq.setMemberId(id);
		faq.setQuestion(question);
		faq.setAnswer(answer);
		
		int result = dao.insertFaq(faq);
		
		resultMap.put("result", result > 0);
		resultMap.put("message", "정상적으로 새글이 추가되었습니다.");
	}
	
	/**
	 * strNoticeNo값이 있으면 수정 
	 */
	private void updateNotice() {
		String strFaqNo = request.getParameter("faqNo");
		Long faqNo = Long.parseLong(strFaqNo);
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");

		Faq faq = new Faq();
		faq.setFaqNo(faqNo);
		faq.setQuestion(question);
		faq.setAnswer(answer);
		
		int result = dao.updateFaq(faq);
		
		resultMap.put("result", result > 0);
		resultMap.put("message", "정상적으로 수정되었습니다.");
		
	}
	
}
