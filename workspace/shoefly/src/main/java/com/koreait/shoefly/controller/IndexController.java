package com.koreait.shoefly.controller;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.shoefly.command.index.SelectMostPopularCommand;
import com.koreait.shoefly.command.index.SelectRecommandCommand;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {
	
	// field
	private SqlSession sqlSession;
	private SelectMostPopularCommand selectMostPopularCommand;
	private SelectRecommandCommand selectRecommandCommand;

	// mapping
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="selectMostPopular.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectMostPopular(Model model) {
		return selectMostPopularCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="selectRecommand.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectProduct(Model model) {
		return selectRecommandCommand.execute(sqlSession, model);
	}
	
}
