package com.koreait.shoefly.command.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Review;

/**
 * 리뷰 삭제 커맨드 
 * 
 * @author 정유한
 * @see ManagerController
 */
@Component
public class DeleteReviewManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long reviewNo = Long.parseLong(request.getParameter("reviewNo"));

		Review review = new Review();
		review.setReviewNo(reviewNo);
		
		ManagerDAO managerDAO = sqlSession.getMapper(ManagerDAO.class);
		managerDAO.deleteReview(review);
		
		return null;
	}

}
