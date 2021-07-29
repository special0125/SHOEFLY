package com.koreait.shoefly.command.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ProductDAO;
@Component
public class InsertSellApplicationCommand implements ProductCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		try {
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest)map.get("request");
			HttpServletResponse response = (HttpServletResponse)map.get("response");
			
			String memberId = request.getParameter("memberId");
			String productName = request.getParameter("productName");
			int productSize = Integer.parseInt(request.getParameter("productSize"));
			long price = Long.parseLong(request.getParameter("price"));
			long memberAddressNo = Long.parseLong(request.getParameter("memberAddressNo"));
			String addrName = request.getParameter("addrName");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2") == "" ? "null" : request.getParameter("addr2");
			
			ProductDAO productDAO = sqlSession.getMapper(ProductDAO.class);
		
			response.setContentType("text/html; charset=utf-8");
			if(memberAddressNo == 0) {
				//기존에 등록되어있는 주소가 아닌 사용자가 새로 등록한 주소 -> MemberAddress 테이블에 삽입해주고 판매신청서등록도 진행
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
				int result1 = productDAO.insertSellApplication(memberId, productSize, productName, price, maxMemberAddressNo);
				
				//판매신청서 완료
				if(result1 > 0) {
					response.getWriter().println("<script>");
					response.getWriter().println("alert('판매신청이 완료되었습니다.')");
					response.getWriter().println("location.href='productListPage.do'");
					response.getWriter().println("</script>");		
				}
				//판매신청서 실패
				else {
					response.getWriter().println("<script>");
					response.getWriter().println("alert('판매신청에 실패하였습니다.')");
					response.getWriter().println("history.back()");
					response.getWriter().println("</script>");
				}
				
			}else {
				//기존에 등록되어있는 주소임 주소등록필요X -> 판매신청서등록진행
				int result2 = productDAO.insertSellApplication(memberId, productSize, productName, price, memberAddressNo);
				
				//판매신청서 완료
				if(result2 > 0) {
					response.getWriter().println("<script>");
					response.getWriter().println("alert('판매신청이 완료되었습니다.')");
					response.getWriter().println("location.href='productListPage.do'");
					response.getWriter().println("</script>");
				}
				//판매신청서 실패
				else {
					response.getWriter().println("<script>");
					response.getWriter().println("alert('판매신청에 실패하였습니다.')");
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
