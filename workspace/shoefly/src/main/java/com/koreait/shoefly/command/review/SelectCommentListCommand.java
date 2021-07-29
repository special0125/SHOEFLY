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
import com.koreait.shoefly.dto.ReviewComment;
import com.koreait.shoefly.util.PagingUtils;

@Component
public class SelectCommentListCommand implements ReviewCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		ReviewDAO reviewDAO = sqlSession.getMapper(ReviewDAO.class);
		
		int nowPage = Integer.parseInt(request.getParameter("page"));
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		
		int totalRecord = reviewDAO.countReviewComment(reviewNo);
		
		Page page = PagingUtils.getPage(nowPage, totalRecord);
		
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("beginRecord", page.getBeginRecord());
		searchMap.put("endRecord", page.getEndRecord());
		searchMap.put("reviewNo", reviewNo);
		
		List<ReviewComment> commentList = reviewDAO.selectCommentList(searchMap);
		
		String path = "select.do?reviewNo=" + reviewNo;
		String paging = PagingUtils.getPaging(path, page);
		
		Map<String, Object> resultMap = new HashMap<>();

		
		if (commentList.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("page", page);
			resultMap.put("paging", paging);
		}else {
			resultMap.put("status", 200);
			resultMap.put("commentList", commentList);
			resultMap.put("page", page);
			resultMap.put("paging", paging);
		}
		
		
		return resultMap;
	}

}
