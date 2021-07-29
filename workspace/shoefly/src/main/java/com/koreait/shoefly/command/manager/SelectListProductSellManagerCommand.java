package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;
import com.koreait.shoefly.dto.Page;
import com.koreait.shoefly.dto.ProductSell;
import com.koreait.shoefly.util.PagingUtils;

/**
 * 상품판매신청목록 전체/검색 커맨드
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectListProductSellManagerCommand implements ManagerCommand {

	private int recordPerPage = 8;
	private int pagePerBlock = 5;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		int state = Integer.parseInt(request.getParameter("state"));
		String memberId = request.getParameter("memberId");
		String productName = request.getParameter("productName") ;
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("state", state);
		searchMap.put("memberId", memberId == null || memberId.isEmpty() ? null : "%" + memberId  + "%");
		searchMap.put("productName", productName == null || productName.isEmpty() ? null : "%" + productName + "%");
		
		int nowpage = Integer.parseInt(request.getParameter("page"));
		int totalRecord = dao.countProductSell(searchMap);
		Page page = PagingUtils.getPage(nowpage, totalRecord, recordPerPage, pagePerBlock);
		
		searchMap.put("order", request.getParameter("order"));
		searchMap.put("isDesc", request.getParameter("isDESC"));
		searchMap.put("beginRecord", page.getBeginRecord());
		searchMap.put("endRecord", page.getEndRecord());
		
		List<ProductSell> list = dao.selectListProductSell(searchMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", list != null && list.size() > 0);
		resultMap.put("list", list);
		resultMap.put("page", page);
		
		return resultMap;
	}
	
}
