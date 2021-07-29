package com.koreait.shoefly.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.shoefly.command.review.DeleteCommentCommand;
import com.koreait.shoefly.command.review.DeleteReviewCommand;
import com.koreait.shoefly.command.review.InsertCommentCommand;
import com.koreait.shoefly.command.review.InsertReviewCommand;
import com.koreait.shoefly.command.review.SelectCommentListCommand;
import com.koreait.shoefly.command.review.SelectProductCommand;
import com.koreait.shoefly.command.review.SelectReviewCommand;
import com.koreait.shoefly.command.review.SelectReviewListCommand;
import com.koreait.shoefly.command.review.UpdateCommentCommand;
import com.koreait.shoefly.command.review.UpdateReviewCommand;
import com.koreait.shoefly.command.review.UpdateReviewPageCommand;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("review")
@AllArgsConstructor
public class ReviewController {

	private SqlSession sqlSession;
	private SelectReviewListCommand selectReviewListCommand;
	private SelectProductCommand selectProductCommand;
	private InsertReviewCommand insertReviewCommand;
	private SelectReviewCommand selectReviewCommand;
	private UpdateReviewPageCommand updateReviewPageCommand;
	private UpdateReviewCommand updateReviewCommand;
	private SelectCommentListCommand selectCommentListCommand;
	private InsertCommentCommand insertCommentCommand;
	private DeleteReviewCommand deleteReviewCommand;
	private UpdateCommentCommand updateCommentCommand;
	private DeleteCommentCommand deleteCommentCommand;
	

	@GetMapping("listPage.do")
	public String listPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		selectReviewListCommand.execute(sqlSession, model);
		return "review/list";
	}
	
	@GetMapping("insertPage.do")
	public String inserinsertReviewPage() {
		return "review/insertReviewPage";
	}
	
	@PostMapping(value="selectProduct.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectProduct(Model model) {
		return selectProductCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="insert.do")
	public String insertReview(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		insertReviewCommand.execute(sqlSession, model);
		return "redirect:listPage.do";
	}
	
	@GetMapping(value="select.do") 
	public String selectReview(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		selectReviewCommand.execute(sqlSession, model);
		return "review/selectReview";
	}
	
	@PostMapping(value="updatePage.do")
	public String updateReviewPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		updateReviewPageCommand.execute(sqlSession, model);
		return "review/updateReviewPage";
	}
	
	@PostMapping(value="update.do")
	public String updateReview(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		updateReviewCommand.execute(sqlSession, model);
		return "redirect:select.do?reviewNo=" + multipartRequest.getParameter("reviewNo") + "&page=1";
	}
	
	@PostMapping(value="delete.do")
	public String deleteReview(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		deleteReviewCommand.execute(sqlSession, model);
		return "redirect:listPage.do";
	}
	
/* =====================REVIEW COMMENT=============================================*/
	
	@PostMapping(value="selectCommentList.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectCommentList(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return selectCommentListCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="insertComment.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> insertComment(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return insertCommentCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="updateComment.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateComment(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return updateCommentCommand.execute(sqlSession, model);
	}

	@RequestMapping(value="deleteComment.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteComment(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return deleteCommentCommand.execute(sqlSession, model);
	}
	
}
