package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Notice;
import com.koreait.shoefly.dto.Page;
import com.koreait.shoefly.util.PagingUtils;

/**
 * 공지사항 전체/검색 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectListNoticeManagerCommand implements ManagerCommand {
	
	private int recordPerPage = 5;
	private int pagePerBlock = 5;

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		Map<String, Object> searchMap = new HashMap<>();
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		if(query == null)
			query = "";
		if(column != null && !column.isEmpty())
			if(column.equals("POSTDATE")) {
				searchMap.put("column", column);
				searchMap.put("startDate", request.getParameter("startDate"));
				searchMap.put("endDate", request.getParameter("endDate"));
			} else {
				searchMap.put("column", column);
				searchMap.put("query", query);
			}
		
		String strNowpage = request.getParameter("page");
		int nowpage = Integer.parseInt(strNowpage == null || strNowpage.equals("") ? "1" : strNowpage);
		int totalRecord = dao.countNotice(searchMap);
		Page page = PagingUtils.getPage(nowpage, totalRecord, recordPerPage, pagePerBlock);

		searchMap.put("beginRecord", page.getBeginRecord());
		searchMap.put("endRecord", page.getEndRecord());
		List<Notice> list = dao.selectListNotice(searchMap);
		
		String path = "noticeListPage.do";
		if(searchMap.get("query") != null) {
			path += "?query=" + (String)searchMap.get("query");
		}
		String paging = PagingUtils.getPaging(path, page);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("paging", paging);
		return null;
	}

}
