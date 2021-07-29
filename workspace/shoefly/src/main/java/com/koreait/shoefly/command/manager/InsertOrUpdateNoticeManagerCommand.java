package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Member;
import com.koreait.shoefly.dto.Notice;

/**
 * 공지사항 작성 및 수정 커맨드
 * 
 * @author 박세환
 */
@Component
public class InsertOrUpdateNoticeManagerCommand implements ManagerCommand {

	private Map<String, Object> resultMap;
	private HttpServletRequest request;
	private ManagerDAO dao;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		this.dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		this.request = (HttpServletRequest)modelMap.get("request");
		this.resultMap = new HashMap<>();
		
		String strNoticeNo = request.getParameter("noticeNo");
		if(strNoticeNo == null || strNoticeNo.isEmpty()) {
			insertNotice();  // strNoticeNo값이 없으면
		} else {
			updateNotice();  // strNoticeNo값이 있으면
		}
		
		return resultMap;
	}

	/**
	 * strNoticeNo값이 없으면 새글 추가
	 */
	private void insertNotice() {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		String id = loginMember.getMemberId();
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setMemberId(id);
		
		int result = dao.insertNotice(notice);
		
		resultMap.put("result", result > 0);
		resultMap.put("message", "정상적으로 새글이 추가되었습니다.");
	}
	
	/**
	 * strNoticeNo값이 있으면 수정 
	 */
	private void updateNotice() {
		String strNoticeNo = request.getParameter("noticeNo");
		Long noticeNo = Long.parseLong(strNoticeNo);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNoticeNo(noticeNo);
		notice.setTitle(title);
		notice.setContent(content);
		
		int result = dao.updateNotice(notice);
		
		resultMap.put("result", result > 0);
		resultMap.put("message", "정상적으로 수정되었습니다.");
		
	}
	
}