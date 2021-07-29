package com.koreait.shoefly.command.review;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.shoefly.dao.ReviewDAO;

@Component
public class InsertReviewCommand implements ReviewCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		
		String loginId = multipartRequest.getParameter("loginId");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String productName = multipartRequest.getParameter("productName");
		MultipartFile file = multipartRequest.getFile("file");
		
		ReviewDAO reviewDAO = sqlSession.getMapper(ReviewDAO.class);
		
		if (file != null && !file.isEmpty()) {
			
			String originalFilename = file.getOriginalFilename();
			
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String filename = "sf_review_" + System.currentTimeMillis();
			String uploadFilename = filename + "." + extension;
			
			String realPath = multipartRequest.getServletContext().getRealPath("resources/archive/review");
			
			File review = new File(realPath);
			if ( !review.exists() ) {
				review.mkdir();
			}
			
			File attach = new File(review, uploadFilename);
			try {
				file.transferTo(attach);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				uploadFilename = URLEncoder.encode(uploadFilename, "utf-8");
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			reviewDAO.insertReview(loginId, productName, title, content, uploadFilename);
			
		}else {
			
			reviewDAO.insertReview(loginId, productName, title, content, "");
			
		}
		
		
		
		
		return null;
	}

}
