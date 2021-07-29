package com.koreait.shoefly.command.review;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ReviewDAO;
import com.koreait.shoefly.dto.ReviewComment;

@Component
public class UpdateCommentCommand implements ReviewCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int reviewCommentNo = Integer.parseInt(request.getParameter("reviewCommentNo"));
		String context = request.getParameter("context");

		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setReviewCommentNo(reviewCommentNo);
		reviewComment.setContext(context);
		
		ReviewDAO reviewDAO = sqlSession.getMapper(ReviewDAO.class);
		
		int result = reviewDAO.updateComment(reviewComment);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		
		return resultMap;
	}

}
