package com.koreait.shoefly.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.shoefly.command.product.SelectBuyApplicationCommand;
import com.koreait.shoefly.command.product.SelectBuyCompleteCommand;
import com.koreait.shoefly.command.product.SelectBuyNowCommand;
import com.koreait.shoefly.command.product.InsertBuyApplicationCommand;
import com.koreait.shoefly.command.product.InsertBuyCommand;
import com.koreait.shoefly.command.product.InsertSellApplicationCommand;
import com.koreait.shoefly.command.product.InsertSellCommand;
import com.koreait.shoefly.command.product.SelectAllListCommand;
import com.koreait.shoefly.command.product.SelectConditionCommand;
import com.koreait.shoefly.command.product.SelectPriceBySizeCommand;
import com.koreait.shoefly.command.product.SelectProductByProductNoCommand;
import com.koreait.shoefly.command.product.SelectSellApplicationCommand;
import com.koreait.shoefly.command.product.SelectSellCompleteCommand;
import com.koreait.shoefly.command.product.SelectSellNowCommand;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {

	private SqlSession sqlSession;
	private SelectAllListCommand selectAllListCommand;
	private SelectConditionCommand selectConditionCommand;
	private SelectProductByProductNoCommand selectProductByProductNoCommand;
	private SelectPriceBySizeCommand selectPriceBySizeCommand;
	private SelectBuyApplicationCommand selectBuyApplicationCommand;
	private SelectSellApplicationCommand selectSellApplicationCommand;
	private InsertBuyApplicationCommand insertBuyApplicationCommand;
	private SelectBuyNowCommand selectBuyNowCommand;
	private SelectSellNowCommand selectSellNowCommand;
	private InsertSellApplicationCommand insertSellApplicationCommand;
	private InsertBuyCommand insertBuyCommand;
	private InsertSellCommand insertSellCommand;
	private SelectSellCompleteCommand selectSellCompleteCommand;
	private SelectBuyCompleteCommand selectBuyCompleteCommand;
	
	//전체 상품 종류 조회
	@GetMapping("productListPage.do")
	public String listPage(HttpServletRequest request,
						   Model model) {
		model.addAttribute("request", request);
		selectAllListCommand.execute(sqlSession, model);
		return "product/productList";
	}
	//제품 선택 조건에 따른 조회(검색, 브랜드, 사이즈, 가격범위)
	@GetMapping("selectCondition.do")
	public String selectCondition(HttpServletRequest request,
								  Model model) {
		model.addAttribute("request", request);
		selectConditionCommand.execute(sqlSession, model);
		return "product/productList";
	}
	
	//제품 상세 페이지로 이동
	@GetMapping("viewProductPage.do")
	public String viewProductPage(HttpServletRequest request,
								  Model model) {
		model.addAttribute("request", request);
		selectProductByProductNoCommand.execute(sqlSession, model);
		return "product/productView";
	}
	
	//AJAX처리_제품 사이즈 선택시 즉시판매가, 즉시구매가 조회
	@GetMapping(value="selectPriceBySize.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectPriceBySize(HttpServletRequest request,
												 HttpSession session,
												 Model model){
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		return selectPriceBySizeCommand.execute(sqlSession, model);
	}
	
	//즉시구매로이동
	@PostMapping("buyNow.do")
	public String buy(HttpServletRequest request,
					  HttpSession session,
				      Model model) {
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		selectBuyNowCommand.execute(sqlSession, model);
		return "product/buyNow";
	}
	
	//즉시판매로이동
	@PostMapping("sellNow.do")
	public String sellNow(HttpServletRequest request,
			 			  HttpSession session,
						  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		selectSellNowCommand.execute(sqlSession, model);
		return "product/sellNow";
	}
	
	//구매신청서 이동
	@PostMapping("buyApplication.do")
	public String buyApplication(HttpServletRequest request,
								 HttpSession session,
			   					 Model model) {
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		selectBuyApplicationCommand.execute(sqlSession, model);
		return "product/buyApplication";
	}
	
	//판매신청서 이동
	@PostMapping("sellApplication.do")
	public String sellApplication(HttpServletRequest request,
								  HttpSession session,
				   				  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		selectSellApplicationCommand.execute(sqlSession, model);
		return "product/sellApplication";
	}
	
	/**
	 * 주소 팝업창 띄우기
	 *
	 * @author 박세환
	 * @return
	 */
	@RequestMapping("jusoPopup.do")
	public String jsuoPopup() {
		return "common/jusoPopup";
	}
	
	//구매신청서 작성
	@PostMapping("insertBuyApplication.do")
	public String insertBuyApplication(HttpServletRequest request,
									   HttpServletResponse response,
			 						   Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		insertBuyApplicationCommand.execute(sqlSession, model);
		return null;
	}
	
	//판매신청서 작성
	@PostMapping("insertSellApplication.do")
	public String insertSellApplication(HttpServletRequest request,
									    HttpServletResponse response,
			 						    Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		insertSellApplicationCommand.execute(sqlSession, model);
		return null;
	}
	
	//구매
	@PostMapping("buy.do")
	public String buy(HttpServletRequest request,
					  HttpServletResponse response,
					  HttpSession session,
					  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		model.addAttribute("session", session);
		insertBuyCommand.execute(sqlSession, model);
		return null;
	}

	//판매
	@PostMapping("sell.do")
	public String sell(HttpServletRequest request,
					  HttpServletResponse response,
					  HttpSession session,
					  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		model.addAttribute("session", session);
		insertSellCommand.execute(sqlSession, model);
		return null;
	}
	
	//판매완료
	@GetMapping("sellComplete.do")
	public String sellComplete(HttpServletRequest request,
							   HttpSession session,
							   Model model) {
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		selectSellCompleteCommand.execute(sqlSession, model);
		return "product/sellComplete";
	}
	
	//구매완료
	@GetMapping("buyComplete.do")
	public String buyComplete(HttpServletRequest request,
			   HttpSession session,
			   Model model) {
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		selectBuyCompleteCommand.execute(sqlSession, model);
		return "product/buyComplete";
	}
	
	//구매신청서, 판매신청서 PopUp
	@GetMapping("applicationInfoPopUp.do")
	public String applicationInfoPopUp() {
		return "product/applicationInfoPopUp";
	}
}
