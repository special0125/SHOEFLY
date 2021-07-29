package com.koreait.shoefly.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.shoefly.command.notice.SelectListFaqNoticeCommand;
import com.koreait.shoefly.command.notice.SelectListNoticeNoticeCommand;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("notice")
@AllArgsConstructor
public class NoticeController {

	private SqlSession sqlSession;
	private SelectListNoticeNoticeCommand selectListNoticeNoticeCommand;
	private SelectListFaqNoticeCommand selectListFaqNoticeCommand;
	
	@GetMapping("noticeListPage.do")
	public String noticeListPage(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		selectListNoticeNoticeCommand.execute(sqlSession, model);
		return "notice/noticeList";
	}
	
	@GetMapping("faqListPage.do")
	public String faqListPage(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		selectListFaqNoticeCommand.execute(sqlSession, model);
		return "notice/faqList";
	}
	
}