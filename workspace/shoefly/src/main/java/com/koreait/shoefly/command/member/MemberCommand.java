package com.koreait.shoefly.command.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koreait.shoefly.command.BaseCommand;

public interface MemberCommand extends BaseCommand {

	public Logger logger = LoggerFactory.getLogger(MemberCommand.class);

}
