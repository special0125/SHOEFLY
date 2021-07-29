package com.koreait.shoefly.dao;

import java.util.List;
import java.util.Map;

import com.koreait.shoefly.dto.Faq;
import com.koreait.shoefly.dto.Notice;

public interface NoticeDAO {

	public int countNotice(Map<String, Object> map);
	public List<Notice> selectListNotice(Map<String, Object> map);
	public int countFaq(Map<String, Object> map);
	public List<Faq> selectListFaq(Map<String, Object> map);
	
}
