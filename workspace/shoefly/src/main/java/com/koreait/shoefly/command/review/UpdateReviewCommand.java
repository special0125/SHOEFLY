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
public class UpdateReviewCommand implements ReviewCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		
		int reviewNo = Integer.parseInt(multipartRequest.getParameter("reviewNo"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String productName = multipartRequest.getParameter("productName");
		String oldfilename = multipartRequest.getParameter("filename");
		
		MultipartFile file = multipartRequest.getFile("file");
		
		System.out.println(oldfilename);
		
		ReviewDAO reviewDAO = sqlSession.getMapper(ReviewDAO.class);
		
		if (file != null && !file.isEmpty()) {
			
			String deleteFilename = multipartRequest.getParameter("filename");
			String realPath = multipartRequest.getServletContext().getRealPath("resources/archive/review");
			File deletefile = new File(realPath, deleteFilename);
			if (deletefile != null) {
				if(deletefile.exists()) {
					deletefile.delete();
				}
			}
			
			
			String originalFilename = file.getOriginalFilename();
			
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String filename = "sf_review_" + System.currentTimeMillis();
			String uploadFilename = filename + "." + extension;
			
			
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
			
			reviewDAO.updateReview(title, content, productName, uploadFilename, reviewNo);
			
		}else {
			
			/*String deleteFilename = multipartRequest.getParameter("filename");
			String realPath = multipartRequest.getServletContext().getRealPath("resources/archive/review");
			File deletefile = new File(realPath, deleteFilename);
			if (deletefile != null) {
				if(deletefile.exists()) {
					deletefile.delete();
				}
			}*/
			reviewDAO.updateReview(title, content, productName, oldfilename, reviewNo);
			
		}
		
		
		return null;
	}

}
