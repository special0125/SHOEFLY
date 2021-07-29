package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Page;
import com.koreait.shoefly.dto.Review;
import com.koreait.shoefly.util.PagingUtils;

/**
 * Review 전체/검색 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectListReviewManagerCommand implements ManagerCommand {

	private int recordPerPage = 5;
	private int pagePerBlock = 5;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		ManagerDAO managerDAO = sqlSession.getMapper(ManagerDAO.class);
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		// getParameter
		String strNowPage = request.getParameter("page");
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int nowPage = Integer.parseInt(strNowPage == null || strNowPage.equals("") ? "1" : strNowPage);

		// totalRecord And SearchMap
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("column", column);
		searchMap.put("query", "%" + query + "%");
		searchMap.put("startDate", startDate);
		searchMap.put("endDate", endDate);
		int totalRecord = managerDAO.countReview(searchMap);
		
		// SelectListReview
		Page page = PagingUtils.getPage(nowPage, totalRecord, recordPerPage, pagePerBlock);
		searchMap.put("beginRecord", page.getBeginRecord());
		searchMap.put("endRecord", page.getEndRecord());
		List<Review> review = managerDAO.selectListReview(searchMap);
		
		// Make PagingPath And Paging for HTML 
		StringBuilder path = new StringBuilder();
		path.append("reviewListPage.do");
		if(column != null && !column.isEmpty()) {
			path.append("?column=").append(column);
			if(query != null && !query.isEmpty()) {
				path.append("&query=").append(query);
			}
			if(startDate != null && !startDate.isEmpty()) {
				path.append("&startDate=").append(startDate);
			}
			if(endDate != null && !endDate.isEmpty()) {
				path.append("endDate=").append(endDate);
			}
		}
		String paging = PagingUtils.getPaging(path.toString(), page);
		
		// AddAttribute
		model.addAttribute("review", review);
		model.addAttribute("page", page);
		model.addAttribute("paging", paging);
		
		return null;
	}

}
