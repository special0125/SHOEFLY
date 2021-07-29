package com.koreait.shoefly.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.shoefly.command.manager.DeleteFaqManagerCommand;
import com.koreait.shoefly.command.manager.DeleteMemberManagerCommand;
import com.koreait.shoefly.command.manager.DeleteNoticeManagerCommand;
import com.koreait.shoefly.command.manager.DeleteProductManagerCommand;
import com.koreait.shoefly.command.manager.DeleteReviewManagerCommand;
import com.koreait.shoefly.command.manager.InsertOrUpdateFaqManagerCommand;
import com.koreait.shoefly.command.manager.InsertOrUpdateNoticeManagerCommand;
import com.koreait.shoefly.command.manager.RestoreReviewManagerCommand;
import com.koreait.shoefly.command.manager.SelectListFaqManagerCommand;
import com.koreait.shoefly.command.manager.SelectListMemberAddressManagerCommand;
import com.koreait.shoefly.command.manager.SelectListMemberManagerCommand;
import com.koreait.shoefly.command.manager.SelectListNoticeManagerCommand;
import com.koreait.shoefly.command.manager.SelectListProductBuyManagerCommand;
import com.koreait.shoefly.command.manager.SelectListProductManagerCommand;
import com.koreait.shoefly.command.manager.SelectListProductSellManagerCommand;
import com.koreait.shoefly.command.manager.SelectListReviewManagerCommand;
import com.koreait.shoefly.command.manager.SelectOneFaqManagerCommand;
import com.koreait.shoefly.command.manager.SelectOneMemberAddressManagerCommand;
import com.koreait.shoefly.command.manager.SelectOneMemberManagerCommand;
import com.koreait.shoefly.command.manager.SelectOneNoticeManagerCommand;
import com.koreait.shoefly.command.manager.SelectOneProductManagerCommand;
import com.koreait.shoefly.command.manager.UpdateMemberPwManagerCommand;
import com.koreait.shoefly.command.manager.UpdateProdcutStateManagerCommand;
import com.koreait.shoefly.command.manager.UpdateProductBuyStateManagerCommand;
import com.koreait.shoefly.command.manager.UpdateProductSellStateManagerCommand;

import lombok.AllArgsConstructor;

/**
 * 관리자 페이지 컨트롤러
 * 
 * @author 박세환
 */
@Controller
@RequestMapping("manager")
@AllArgsConstructor
public class ManagerController {

	// SQL
	private SqlSession sqlSession;
	// MEMBER
	private SelectListMemberManagerCommand selectListMemberManagerCommand;
	private SelectOneMemberManagerCommand selectOneMemberManagerCommand;
	private DeleteMemberManagerCommand deleteMemberManagerCommand;
	private SelectListMemberAddressManagerCommand selectListMemberAddressManagerCommand;
	private UpdateMemberPwManagerCommand updateMemberPwManagerCommand;
	// PRODUCT
	private SelectListProductManagerCommand selectListProductManagerCommand;
	private SelectOneProductManagerCommand selectOneProductManagerCommand;
	private UpdateProdcutStateManagerCommand updateProdcutStateManagerCommand;
	private DeleteProductManagerCommand deleteProductManagerCommand;
	// PRDOCUT BUY
	private SelectListProductBuyManagerCommand selectListProductBuyManagerCommand;
	private SelectOneMemberAddressManagerCommand selectOneMemberAddressManagerCommand;
	private UpdateProductBuyStateManagerCommand updateProductBuyStateManagerCommand;
	// PRODUCT SELL
	private SelectListProductSellManagerCommand selectListProductSellManagerCommand;
	private UpdateProductSellStateManagerCommand updateProductSellStateManagerCommand;
	// NOTICE
	private SelectListNoticeManagerCommand selectListNoticeManagerCommand;
	private SelectOneNoticeManagerCommand selectOneNoticeManagerCommand;
	private InsertOrUpdateNoticeManagerCommand insertOrUpdateNoticeManagerCommand;
	private DeleteNoticeManagerCommand deleteNoticeManagerCommand;
	// FAQ
	private SelectListFaqManagerCommand selectListFaqManagerCommand;
	private SelectOneFaqManagerCommand selectOneFaqMangerCommand;
	private InsertOrUpdateFaqManagerCommand insertOrUpdateFaqManagerCommand;
	private DeleteFaqManagerCommand deleteFaqManagerCommand;
	// REVIEW
	private SelectListReviewManagerCommand selectListReviewCommand;
	private DeleteReviewManagerCommand deleteReviewCommand;
	private RestoreReviewManagerCommand restoreReviewCommand;
	
	
	// MEMBER
	/**
	 * 회원관리 페이지 (리스트)
	 */
	@GetMapping(value= {"/","memberListPage.do"})
	public String memberListPage() {
		return "manager/memberList";
	}
	
	/**
	 * 회원 전체/검색 리스트 출력
	 * 
	 * @see SelectListMemberManagerCommand
	 */
	@ResponseBody
	@PostMapping(value="memberList.do",
				 produces="application/json; charset=UTF-8")
	public Map<String, Object> memberList(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return selectListMemberManagerCommand.execute(sqlSession, model);
	}
	
	/**
	 * 회원관리 페이지 (상세)
	 */
	@GetMapping(value="memberInfoPage.do")
	public String memberInfoPage(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		selectOneMemberManagerCommand.execute(sqlSession, model);
		return "manager/memberInfo";
	}
	
	/**
	 * 회원 삭제처리<br>
	 * 결과 JSON으로 출력
	 * 
	 * @see DeleteMemberManagerCommand
	 */
	@ResponseBody
	@GetMapping(value="deleteMember.do",
			 	produces="application/json; charset=UTF-8")
	public Map<String, Object> deleteMember(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return deleteMemberManagerCommand.execute(sqlSession, model);
	}
	
	/**
	 * 회원 주소관리 페이지
	 */
	@GetMapping(value="memberAddressPage.do")
	public String selectListAddress(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		selectListMemberAddressManagerCommand.execute(sqlSession, model);
		return "manager/memberAddress";
	}
	
	/**
	 * 회원 비밀번호 재설정<br>
	 * 변경된 비밀번호는 회원 이메일에 전송<br>
	 * 전송결과는 JSON으로 출력
	 * 
	 * @see UpdateMemberPwManagerCommand
	 */
	@ResponseBody
	@GetMapping(value="updateMemberPw.do",
		    produces="text/html; charset=UTF-8")
	public String updateMemberPw(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return updateMemberPwManagerCommand.execute(sqlSession, model).get("response").toString();
	}
	
	
	// PRODUCT
	/**
	 * 상품관리 페이지
	 */
	@GetMapping(value="productListPage.do")
	public String productListPage() {
		return "manager/productList";
	}
	
	/**
	 * 상품 전체/선택 리스트 출력<br>
	 * JSON 타입으로 출력
	 * 
	 * @see views/manager/productList.jsp 
	 * @see SelectListProductManagerCommand
	 */
	@ResponseBody
	@GetMapping(value="selectListProduct.do",
				produces="application/json; charset=UTF-8")
	public Map<String, Object> selectListProduct(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return selectListProductManagerCommand.execute(sqlSession, model);
	}

	/**
	 * 상품관리 페이지(상세)
	 * 
	 * @see SelectOneProductManagerCommand
	 */
	@GetMapping(value="productInfoPage.do")
	public String productInfoPage(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		selectOneProductManagerCommand.execute(sqlSession, model);
		return "manager/productInfo";
	}
	
	/**
	 * 상품의 거래 상태를 변경 (거래중 / 중지)
	 * 
	 * @see UpdateProdcutStateManagerCommand
	 */
	@GetMapping(value="updateProductState.do")
	public String updateProductState(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		updateProdcutStateManagerCommand.execute(sqlSession, model);
		return "redirect:productInfoPage.do?no=" + request.getParameter("no");
	}
	
	/**
	 * 상품을 삭제<br>
	 * 미구현
	 * 
	 * @see DeleteProductManagerCommand
	 */
	@ResponseBody
	@RequestMapping(value="deleteProduct.do",
				 produces="application/json; charset=UTF-8")
	public Map<String, Object> deleteProduct(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return deleteProductManagerCommand.execute(sqlSession, model);
	}
	
	
	// PRDOCUT BUY
	/**
	 * 상품 구매신청관리 페이지 
	 * 
	 * @see views/manager/productBuyList.jsp
	 */
	@GetMapping(value="productBuyListPage.do")
	public String productBuyListPage() {
		return "manager/productBuyList";
	}
	
	/**
	 * 상품 구매신청 전체/선택 리스트 출력<br>
	 * JSON으로 결과 출력
	 *
	 * @see SelectListProductBuyManagerCommand
	 */
	@ResponseBody
	@GetMapping(value="selectListProductBuy.do",
				produces="application/json; charset=UTF-8")
	public Map<String, Object> selectListProductBuy(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return selectListProductBuyManagerCommand.execute(sqlSession, model);
	}
	
	/**
	 * 회원 주소 선택 출력<br>
	 * JSON으로 결과 출력
	 * 
	 * @see SelectOneMemberAddressManagerCommand
	 */
	@ResponseBody
	@GetMapping(value="selectOneMemberAddress.do",
				produces="application/json; charset=UTF-8")
	public Map<String, Object> selectOneMemberAddress(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return selectOneMemberAddressManagerCommand.execute(sqlSession, model);
	}
	
	/**
	 * 상품 구매신청 State 변경<br>
	 * JSON으로 결과 출력
	 *
	 * @see UpdateProductBuyStateManagerCommand
	 */
	@ResponseBody
	@GetMapping(value="updateProductBuyState.do",
				produces="application/json; charset=UTF-8")
	public Map<String, Object> updateProductBuyState(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return updateProductBuyStateManagerCommand.execute(sqlSession, model);
	}
	
	
	// PRODUCT SELL 
	/**
	 * 상품 판매신청 관리 페이지
	 *
	 * @see views/manager/productSellList.jsp
	 */
	@GetMapping(value="productSellListPage.do")
	public String productSellListPage() {
		return "manager/productSellList";
	}

	/**
	 * 상품 판매신청 전체/선택 리스트 출력<br>
	 * JSON으로 출력
	 * 
	 * @see SelectListProductSellManagerCommand
	 */
	@ResponseBody
	@GetMapping(value="selectListProductSell.do",
				produces="application/json; charset=UTF-8")
	public Map<String, Object> selectListProductSell(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return selectListProductSellManagerCommand.execute(sqlSession, model);
	}
	
	/**
	 * 상품 판매신청 state 변경<br>
	 * 결과를 JSON으로 출력
	 *
	 * @see UpdateProductSellStateManagerCommand
	 */
	@ResponseBody
	@GetMapping(value="updateProductSellState.do",
				produces="application/json; charset=UTF-8")
	public Map<String, Object> updateProductSellState(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return updateProductSellStateManagerCommand.execute(sqlSession, model);
	}
	
	
	// NOTICE
	/**
	 * 공지사항 관리 페이지
	 *
	 * @see SelectListNoticeManagerCommand
	 */
	@GetMapping("noticeListPage.do")
	public String noticeListPage(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		selectListNoticeManagerCommand.execute(sqlSession, model);
		return "manager/noticeList";
	}

	/**
	 * 공지사항 관리 페이지 (상세)
	 *
	 * @see SelectOneNoticeManagerCommand
	 */
	@GetMapping("noticeInfoPage.do")
	public String noticeInfoPage(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request); 
		selectOneNoticeManagerCommand.execute(sqlSession, model);
		return "manager/noticeInfo";
	}
	
	/**
	 * 공지사항 추가/수정 처리<br>
	 * 결과를 JSON으로 출력
	 *
	 * @see InsertOrUpdateNoticeManagerCommand
	 */
	@ResponseBody
	@PostMapping(value="insertNotice.do",
				 produces="application/json; charset=UTF-8")
	public Map<String, Object> insertNotice(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return insertOrUpdateNoticeManagerCommand.execute(sqlSession, model);
	}

	/**
	 * 공지사항 삭제 처리<br>
	 * 결과를 JSON으로 출력
	 *
	 * @see DeleteNoticeManagerCommand
	 */
	@ResponseBody
	@PostMapping(value="deleteNotice.do",
				 produces="application/json; charset=UTF-8")
	public Map<String, Object> deleteNotice(
			Model model,
			HttpServletRequest request){
		model.addAttribute("request", request);
		return deleteNoticeManagerCommand.execute(sqlSession, model);
	}
	
	
	// FAQ
	/**
	 * FAQ 게시판 관리 페이지
	 *
	 * @see SelectListFaqManagerCommand
	 */
	@GetMapping("faqListPage.do")
	public String faqListPage(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		selectListFaqManagerCommand.execute(sqlSession, model);
		return "manager/faqList";
	}
	
	/**
	 * 게시판 작성 및 수정 페이지
	 *
	 * @see SelectOneFaqManagerCommand
	 */
	@GetMapping("faqInfoPage.do")
	public String faqInfoPage(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request); 
		selectOneFaqMangerCommand.execute(sqlSession, model);
		return "manager/faqInfo";
	}

	/**
	 * FAQ 작성 및 수정<br>
	 * JSON으로 결과 출력
	 * 
	 * @see InsertOrUpdateFaqManagerCommand
	 */
	@ResponseBody
	@PostMapping(value="insertFaq.do",
				 produces="application/json; charset=UTF-8")
	public Map<String, Object> insertFaq(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return insertOrUpdateFaqManagerCommand.execute(sqlSession, model);
	}
	
	/**
	 * FAQ 삭제<br>
	 * JSON으로 결과 출력
	 *
	 * @see DeleteFaqManagerCommand
	 */
	@ResponseBody
	@PostMapping(value="deleteFaq.do",
				 produces="application/json; charset=UTF-8")
	public Map<String, Object> deleteFaq(
			Model model,
			HttpServletRequest request) {
		model.addAttribute("request", request);
		return deleteFaqManagerCommand.execute(sqlSession, model);
	}
	
	
	// REVIEW
	/**
	 * 후기관리 페이지
	 *  
	 * @author 정유한
	 */
	@GetMapping("reviewListPage.do")
	public String reviewListPage(HttpServletRequest request,
								 Model model) {
		model.addAttribute("request", request);
		selectListReviewCommand.execute(sqlSession, model);
		return "manager/reviewList";
	}
	
	/**
	 * 후기를 삭제처리합니다.<br>
	 * reviewListPage.do로 리다이렉트 합니다.
	 * 
	 * @author 정유한
	 * @see DeleteReviewManagerCommand
	 */
	@GetMapping("deleteReview.do")
	public String deleteReview(HttpServletRequest request,
							   Model model) {
		model.addAttribute("request", request);
		deleteReviewCommand.execute(sqlSession, model);
		return "redirect:reviewListPage.do";
	}
	
	/**
	 * 삭제처리한 후기를 복구합니다.<br>
	 * reviewListPage.do로 리다이렉트 합니다.
	 * 
	 * @author 정유한
	 * @see RestoreReviewManagerCommand
	 */
	@GetMapping("restoreReview.do")
	public String restoreReview(HttpServletRequest request,
								Model model) {
		model.addAttribute("request", request);
		restoreReviewCommand.execute(sqlSession, model);
		return "redirect:reviewListPage.do";
	}
}
