package com.koreait.shoefly.command.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koreait.shoefly.command.BaseCommand;
import com.koreait.shoefly.command.member.MemberCommand;

public interface ProductCommand extends BaseCommand{

	public Logger logger = LoggerFactory.getLogger(MemberCommand.class);
	
}
