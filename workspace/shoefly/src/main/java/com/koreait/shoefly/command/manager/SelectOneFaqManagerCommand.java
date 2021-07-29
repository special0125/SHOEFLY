package com.koreait.shoefly.command.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Faq;

/**
 * FAQ 상세보기 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectOneFaqManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		String strNo = request.getParameter("no");
		if(strNo != null && !strNo.isEmpty()) {
			long no = Long.parseLong(strNo);
			Faq faq = dao.selectOneFaq(no);
			model.addAttribute("faq", faq);
		}
		
		return null;
	}

}
