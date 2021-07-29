package com.koreait.shoefly.dao;

import java.util.List;

import com.koreait.shoefly.dto.Member;
import com.koreait.shoefly.dto.MemberAddress;
import com.koreait.shoefly.dto.ProductBuy;
import com.koreait.shoefly.dto.ProductSell;

public interface MemberDAO {

	public Member login(Member member);
	
	public int idCheck(String memberId);

	public int join(Member member);

	public int emailOrCheck(String email);
	
	public Member findId(String email);
	
	public String idExists(String memberId);
	
	public Member findPw(Member member);
	
	public Member idEmailEquals(String memberId);
	
	public int updatePw(Member member);

	public int deleteMember(long memberNo);
	
	public int updateName(Member member);
	
	public List<MemberAddress> selectAddrList(long memberNo);
	
	public MemberAddress selectAddrByNo(long memberAddressNo);
	
	public int updateAddress(MemberAddress memberAddress);
	
	public int deleteMemberAddress(MemberAddress memberAddress);

	public int insertAddress(MemberAddress memberAddress);
	
	public int countBuy();
	
	public List<ProductBuy> selectListBuy(int state, String memberId);
	
	public int deleteBuyRequest(long productBuyNo);
	
	public List<ProductSell> selectListSell(int state, String memberId);
	
	public int deleteSellRequest(long productSellNo);
}
