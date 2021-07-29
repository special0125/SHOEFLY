package com.koreait.shoefly.command.review;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ReviewDAO;

@Component
public class DeleteReviewCommand implements ReviewCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		
		ReviewDAO reviewDAO = sqlSession.getMapper(ReviewDAO.class);
		
		reviewDAO.deleteReview(reviewNo);
		
		
		return null;
	}

}
