package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.controller.ManagerController;
import com.koreait.shoefly.dao.ManagerDAO;

/**
 * FAQ 개시글 삭제 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class DeleteFaqManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		long faqNo = Long.parseLong(request.getParameter("faqNo"));
		int result = dao.deleteFaq(faqNo);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result > 0);
		resultMap.put("message", "글이 정상적으로 삭제되었습니다.");
		
		return resultMap;
	}
	
}
