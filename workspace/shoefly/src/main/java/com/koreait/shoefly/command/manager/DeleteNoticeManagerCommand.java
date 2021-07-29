package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;

/**
 * 공지사항 삭제 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class DeleteNoticeManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		int result = dao.deleteNotice(noticeNo);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result > 0);
		resultMap.put("message", "글이 정상적으로 삭제되었습니다.");
		
		return resultMap;
	}
	
}
