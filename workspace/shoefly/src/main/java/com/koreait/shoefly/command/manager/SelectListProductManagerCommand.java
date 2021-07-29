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
import com.koreait.shoefly.dto.Product;
import com.koreait.shoefly.util.PagingUtils;

/**
 * 상품 전체/검색 커맨드 
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class SelectListProductManagerCommand implements ManagerCommand {

	private int recordPerPage = 10;
	private int pagePerBlock = 5;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		String strPage = request.getParameter("page");
		if(strPage == null || strPage.isEmpty())
			strPage = "1";
		int nowpage = Integer.parseInt(strPage);
		String productNo = request.getParameter("productNo");
		if(productNo == null || productNo.isEmpty()) 
			productNo = null;
		String productName = request.getParameter("productName");
		if(productName == null || productName.isEmpty()) 
			productName = null;
		String[] brands = request.getParameterValues("brand");
		if(brands == null || brands.length == 0) 
			brands = null;
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("productNo", productNo);
		searchMap.put("productName", productName);
		searchMap.put("brands", brands);
		
		int totalRecord = dao.countProduct(searchMap);
		
		Page page = PagingUtils.getPage(nowpage, totalRecord, recordPerPage, pagePerBlock);
		
		String order = request.getParameter("order");
		String isDesc = request.getParameter("isDesc");
		
		searchMap.put("order", order);
		searchMap.put("isDesc", isDesc);
		searchMap.put("beginRecord", page.getBeginRecord());
		searchMap.put("endRecord", page.getEndRecord());
		
		List<Product> list = dao.selectListProduct(searchMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", list == null || list.size() == 0 ? false : true);
		resultMap.put("list", list);
		resultMap.put("page", page);
		
		return resultMap;
	}

}
