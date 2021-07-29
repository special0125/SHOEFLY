package com.koreait.shoefly.command.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.koreait.shoefly.dao.ManagerDAO;

/**
 * 상품 삭제 커맨드 (구현 X)
 * 
 * @author 박세환
 * @see ManagerController
 */
@Component
public class DeleteProductManagerCommand implements ManagerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ManagerDAO dao = sqlSession.getMapper(ManagerDAO.class);
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		String productNo = request.getParameter("no");
		
		Map<String, Object> resultMap = new HashMap<>();
		int count = 0;
		
		count = dao.countProductBuyForProduct(productNo);
		if(count > 0) {
			resultMap.put("result", false);
			resultMap.put("message", "구매요청 또는 구매기록이 존재하여 상품을 삭제할 수 없습니다.");
			return resultMap;
		}
		count = dao.countProductSellForProduct(productNo);
		if(count > 0) {
			resultMap.put("result", false);
			resultMap.put("message", "판매요청 또는 판매기록이 존재하여 상품을 삭제할 수 없습니다.");
			return resultMap;
		}
		count = dao.countReviewForProduct(productNo);
		if(count > 0) {
			resultMap.put("result", false);
			resultMap.put("message", "현재 상품과 관련된 리뷰가 존재하여 상품을 삭제할 수 없습니다.");
			return resultMap;
		}
		
		dao.deleteProductDetails(productNo);
		int result = dao.deleteProduct(productNo);
		if(result == 0) {
			logger.info("상품 삭제중 오류가 발생했습니다.");
			resultMap.put("result", false);
			resultMap.put("message", "심각한 오류로 인해 상품을 삭제하지 못하였습니다.");
		} else {
			resultMap.put("result", true);
		}
		
		return resultMap;
	}

}
