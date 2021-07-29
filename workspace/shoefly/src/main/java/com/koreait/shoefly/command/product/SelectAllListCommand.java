package com.koreait.shoefly.command.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ProductDAO;
import com.koreait.shoefly.dto.Page;
import com.koreait.shoefly.util.PagingUtils;
@Component
public class SelectAllListCommand implements ProductCommand {
	
	private int recordPerPage = 8;
	private int pagePerBlock = 5;
	
	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		ProductDAO productDAO = sqlSession.getMapper(ProductDAO.class);
		
		//페이징처리
		Map<String, Object> listMap = new HashMap<>();
		String strNowpage = request.getParameter("page");
		int nowpage = Integer.parseInt(strNowpage == null || strNowpage.equals("") ? "1" : strNowpage);
		int totalRecord = productDAO.countProduct();
		Page page = PagingUtils.getPage(nowpage, totalRecord, recordPerPage, pagePerBlock);

		listMap.put("beginRecord", page.getBeginRecord());
		listMap.put("endRecord", page.getEndRecord());
		
		String path = "productListPage.do";
		String paging = PagingUtils.getPaging(path, page);

		model.addAttribute("list", productDAO.selectAllList(listMap));
		model.addAttribute("page", page);
		model.addAttribute("paging", paging);
		model.addAttribute("totalProduct", totalRecord);

		return null;
	}

}
