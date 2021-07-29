package com.koreait.shoefly.command.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.NoticeDAO;
import com.koreait.shoefly.dto.Notice;
import com.koreait.shoefly.dto.Page;
import com.koreait.shoefly.util.PagingUtils;

@Component
public class SelectListNoticeNoticeCommand implements NoticeCommand{
	
	private int recordPerPage = 5;
	private int pagePerBlock = 5;

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		Map<String, Object> searchMap = new HashMap<>();
		String query = request.getParameter("query");
		searchMap.put("query", query == null || query.equals("") ? null : query);
		
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