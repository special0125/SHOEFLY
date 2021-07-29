package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Member;
import com.koreait.shoefly.dto.Page;
import com.koreait.shoefly.util.PagingUtils;

/**
 * 회원 검색/전체 리스트 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectListMemberManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		String order = request.getParameter("order"); // 정렬
		String isDesc = request.getParameter("isDesc"); // 역순인가
		String column = request.getParameter("column"); // 검색 기준
		String query = request.getParameter("query"); // 검색어
		String strPage = request.getParameter("page"); 
		if(strPage == null || strPage.isEmpty())
			strPage = "1";
		int nowpage = Integer.parseInt(strPage); // 페이지
		String startDate = request.getParameter("startDate"); // 날짜 검색 시작
		String endDate = request.getParameter("endDate"); // 날짜 검색 마지막
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("order", order);
		searchMap.put("isDesc", isDesc);
		searchMap.put("column", column);
		searchMap.put("query", query);
		searchMap.put("nowpage", nowpage);
		searchMap.put("startDate", startDate);
		searchMap.put("endDate", endDate);
		
		int totalRecord = dao.countMember(searchMap);
		int recordPerPage = 10;
		Page page = PagingUtils.getPage(nowpage, totalRecord, recordPerPage, 1);
		searchMap.put("beginRecord", page.getBeginRecord());
		searchMap.put("endRecord", page.getEndRecord());
		
		List<Member> list = dao.selectListMember(searchMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("result", list.size() > 0);
		resultMap.put("page", page);
		return resultMap;
	}

}
