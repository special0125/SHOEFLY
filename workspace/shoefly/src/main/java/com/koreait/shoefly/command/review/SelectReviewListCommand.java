package com.koreait.shoefly.command.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ReviewDAO;
import com.koreait.shoefly.dto.Page;
import com.koreait.shoefly.dto.Review;
import com.koreait.shoefly.util.PagingUtils;

@Component
public class SelectReviewListCommand implements ReviewCommand {

	private int recordPerPage = 10;
	private int pagePerBlock = 5;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
	
			ReviewDAO reviewDAO = sqlSession.getMapper(ReviewDAO.class);
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest)map.get("request");
			
			Map<String, Object> searchMap = new HashMap<>();
			String column =request.getParameter("column");
			String query = request.getParameter("query");
			
			searchMap.put("column", column);
			searchMap.put("query", query);
			
			String strNowPage = request.getParameter("page");
			int nowPage = Integer.parseInt(strNowPage == null || strNowPage.equals("") ? "1" : strNowPage);
			int totalRecord = reviewDAO.countReview(searchMap);
			Page page = PagingUtils.getPage(nowPage, totalRecord, recordPerPage, pagePerBlock);
			
			searchMap.put("beginRecord", page.getBeginRecord());
			searchMap.put("endRecord", page.getEndRecord());
			List<Review> review = reviewDAO.selectListReview(searchMap);
			
			String path = "listPage.do";
			if (searchMap.get("column") != null && searchMap.get("query") != null) {
				path += "?column=" + searchMap.get("column").toString() + "&query=" + searchMap.get("query").toString();
			}
			String paging = PagingUtils.getPaging(path, page);
			
			model.addAttribute("review", review);
			model.addAttribute("page", page);
			model.addAttribute("paging", paging);
			model.addAttribute("column", column);
			
			return null;
		
		
	}

}
