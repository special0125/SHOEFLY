package com.koreait.shoefly.command.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ProductDAO;
import com.koreait.shoefly.dto.Member;
@Component
public class InsertSellCommand implements ProductCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		try {
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest)map.get("request");
			HttpServletResponse response = (HttpServletResponse)map.get("response");
			HttpSession session = (HttpSession)map.get("session");
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			//productDetailNo를 알아내기위해 필요한 productName, productSize
			String productName = request.getParameter("productName");
			int productSize = Integer.parseInt(request.getParameter("productSize"));
			long price = Long.parseLong(request.getParameter("price"));
			//신규배송지인지 기존배송지인지 체크하기 위한 memberAddressNo
			long memberAddressNo = Long.parseLong(request.getParameter("memberAddressNo"));
			String addrName = request.getParameter("addrName");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2") == "" ? "null" : request.getParameter("addr2");
			
			ProductDAO productDAO = sqlSession.getMapper(ProductDAO.class);
			
			response.setContentType("text/html; charset=utf-8");
			if(memberAddressNo == 0) {
				//기존에 등록되어있는 주소가 아닌 사용자가 새로 등록한 주소 -> MemberAddress 테이블에 삽입해주고 판매등록도 진행
				//새로운 주소 등록
				//상세주소 유무로 구분하여 진행
				if(addr2 != "null") {
					productDAO.insertNewAddress(memberId, addrName, addr1, addr2);
				}else {
					productDAO.insertNewAddressNoAddr2(memberId, addrName, addr1);
				}

				//새로등록된 주소의 memberAddressNo을 알기위해
				//maxMemberAddressNo를 조회하기( = 새로 등록된 주소의 memberAddressNo)
				long maxMemberAddressNo = productDAO.maxMemberAddressNo();
				long productBuyNo = productDAO.selectProductBuyNo(productSize, productName, memberId);
				int result1 = productDAO.insertSell(memberId, productName, productSize, price, maxMemberAddressNo, productBuyNo);
				
				//상품 구매자의 구매요청서를 찾아서 구매된것으로 처리
				int result2 = productDAO.updateBuyProduct(productBuyNo);
				
				//등록된 판매 게시글번호
				long productSellNo = productDAO.selectMaxProductSellNo(productBuyNo);
				
				//판매완료
				if(result1 > 0 && result2 > 0) {
					response.getWriter().println("<script>");
					response.getWriter().println("alert('판매가 완료되었습니다.')");
					response.getWriter().println("location.href='sellComplete.do?productSellNo=" + productSellNo + "'");
					response.getWriter().println("</script>");
				//판매 실패
				}else {
					response.getWriter().println("<script>");
					response.getWriter().println("alert('판매에 실패하였습니다.')");
					response.getWriter().println("history.back()");
					response.getWriter().println("</script>");
				}
			}	
			else {
				//기존에 등록되어있는 주소임 주소등록필요X -> 판매등록진행
				//buyAppliocation작성자의 PRODUCT_BUY_NO찾기(중복이 있을 경우 postDate가 오래된 것부터 조회)
				long productBuyNo = productDAO.selectProductBuyNo(productSize, productName, memberId);
				int result3 = productDAO.insertSell( memberId, productName, productSize, price, memberAddressNo, productBuyNo);
				
				//상품 구매자의 구매요청서를 찾아서 구매된것으로 처리
				int result4 = productDAO.updateBuyProduct(productBuyNo);
				
				//등록된 판매 게시글번호
				long productSellNo = productDAO.selectMaxProductSellNo(productBuyNo);

				//판매 완료
				if(result3 > 0 && result4 > 0) {
					response.getWriter().println("<script>");
					response.getWriter().println("alert('판매가 완료되었습니다.')");
					response.getWriter().println("location.href='sellComplete.do?productSellNo=" + productSellNo + "'");
					response.getWriter().println("</script>");
				//판매 실패
				}else {
					response.getWriter().println("<script>");
					response.getWriter().println("alert('판매에 실패하였습니다.')");
					response.getWriter().println("history.back()");
					response.getWriter().println("</script>");
				}
			
			 }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}

}
