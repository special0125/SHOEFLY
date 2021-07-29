package com.koreait.shoefly.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.koreait.shoefly.dto.MemberAddress;
import com.koreait.shoefly.dto.Product;

public interface ProductDAO {
	public List<Product> selectAllList(Map<String, Object> listMap);
	public List<Product> selectCondition(Map<String, Object> paramMap);
	public Product selectProductByProductNo(String productNo);
	public Long selectBuyPriceBySizeNoId(int productSize, String productName);
	public Long selectSellPriceBySizeNoId(int productSize, String productName);
	public Long selectBuyPriceBySize(int productSize, String productName, String memberId);
	public Long selectSellPriceBySize(int productSize, String productName, String memberId);
	public long selectProductSellNo(int productSize, String productName, String memberId);	//판매상품 번호찾기
	public int countProduct();
	public int countConditionProduct(Map<String, Object> paramMap);
	public Product buyApplication(String productName, int productSize);
	public List<MemberAddress> selectMemberAddr(String memberId);
	public int insertNewAddress(String memberId, String addrName, String addr1, String addr2);
	public int insertNewAddressNoAddr2(String memberId, String addrName, String addr1);
	public long maxMemberAddressNo();
	public int insertBuyApplication(String memberId, int productSize, String productName, long price, long memberAddressNo);;
	public int insertSellApplication(String memberId, int productSize, String productName, long price, long memberAddressNo);;
	public int insertBuy(String memberId, int productSize, String productName, long price, long memberAddressNo);
	public long selectMaxProductBuyNo();
	public int updateSellProduct(long MaxproductBuyNo, long productSellNo);
	public long selectProductBuyNo(int productSize, String productName, String memberId);
	public long selectProductSellByNo(long productSellNo);
	public long selectProductBuyByNo(long MaxproductBuyNo);
	public String selectProductDetailInfo1(long productDetailNo);
	public int selectProductDetailInfo2(long productDetailNo);
	public long selectMemberAddrNoInSell(long productSellNo);
	public long selectMemberAddrNoInBuy(long MaxproductBuyNo);
	public Date selectSelldate(long productSellNo);
	public long selectPriceinSell(long productSellNo);
	public long selectPriceinBuy(long MaxproductBuyNo);
	public MemberAddress selectMemberAddrByProductSellNo(long productSellNo);
	public long selectMaxProductSellNo(long productBuyNo);
	public int insertSell(String memberId, String productName, int productSize, long price, long memberAddressNo, long productBuyNo);
	public int updateBuyProduct(long productBuyNo);
}