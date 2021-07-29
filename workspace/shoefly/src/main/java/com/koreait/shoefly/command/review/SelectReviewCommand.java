package com.koreait.shoefly.command.review;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ReviewDAO;
import com.koreait.shoefly.dto.Review;

@Component
public class SelectReviewCommand implements ReviewCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		
		ReviewDAO reviewDAO = sqlSession.getMapper(ReviewDAO.class);
		Cookie viewCookie = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for (int i = 0; i< cookies.length; i++) {
				if (cookies[i].getName().equals("cookie" + reviewNo)) {
					viewCookie = cookies[i];
				}
			}
		}
		
		if (viewCookie == null) {
			try {
				Cookie newCookie = new Cookie("cookie" + reviewNo, "cookie" + reviewNo);
				newCookie.setMaxAge(60 * 60 * 24);
				response.addCookie(newCookie);
				reviewDAO.updateHit(reviewNo);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		Review review = reviewDAO.selectReview(reviewNo);
		model.addAttribute("review", review);
		
		try {
			model.addAttribute("filename", URLDecoder.decode(review.getImage(), "UTF-8"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
